/*
   This file is part of Cyclos.

   Cyclos is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.

   Cyclos is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Cyclos; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 */
package nl.strohalm.cyclos.webservices.rest;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.strohalm.cyclos.entities.access.Channel;
import nl.strohalm.cyclos.entities.access.PrincipalType;
import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.entities.services.ServiceClient;
import nl.strohalm.cyclos.services.access.AccessServiceLocal;
import nl.strohalm.cyclos.services.access.ChannelServiceLocal;
import nl.strohalm.cyclos.services.access.exceptions.BlockedCredentialsException;
import nl.strohalm.cyclos.services.access.exceptions.CredentialsException;
import nl.strohalm.cyclos.services.access.exceptions.InvalidCredentialsException;
import nl.strohalm.cyclos.services.access.exceptions.UserNotFoundException;
import nl.strohalm.cyclos.services.elements.ElementServiceLocal;
import nl.strohalm.cyclos.services.services.ServiceClientServiceLocal;
import nl.strohalm.cyclos.services.transactions.exceptions.InvalidChannelException;
import nl.strohalm.cyclos.utils.access.LoggedUser;
import nl.strohalm.cyclos.webservices.WebServiceContext;
import nl.strohalm.cyclos.webservices.model.ServerErrorVO;
import nl.strohalm.cyclos.webservices.utils.WebServiceHelper;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * An {@link AuthenticationProvider} which validates the username / password in Cyclos. WARN: Can't throw AuthenticationException cause it returns 401
 * UNAUTHORIZED and that is captured by the browser, pops up an authentication form and we don't want that. A 401 freezes the mobile app.
 * @author luis
 */
public class RestAuthenticationProvider implements AuthenticationProvider {


    private ElementServiceLocal elementService;
    private AccessServiceLocal  accessService;
    private ServiceClientServiceLocal serviceClientServiceLocal;
    private ChannelServiceLocal channelService;
    private static final String INVALID_CREDENTIALS          = "INVALID_CREDENTIALS";
    private static final String CHANNEL_DISABLED             = "CHANNEL_DISABLED";
    private static final String BLOCKED_CREDENTIALS          = "BLOCKED_CREDENTIALS";
    private static final String UNKNOWN_AUTHENTICATION_ERROR = "UNKNOWN_AUTHENTICATION_ERROR";
    private static final String[]                BLANK_CREDENTIALS = { "", "" };

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        System.out.println("In authenticator servcie");
        // Get / validate the principal / credentials
        String principal = authentication.getName();
        String credentials = (String) authentication.getCredentials();
        if (StringUtils.isEmpty(principal) || StringUtils.isEmpty(credentials)) {
            sendError("Empty username / password", INVALID_CREDENTIALS);
            throw new InvalidCredentialsException();
        }
        // Get the request
        HttpServletRequest request = WebServiceContext.getRequest();
        if (request == null) {
            sendError("Couldn't resolve the current request", UNKNOWN_AUTHENTICATION_ERROR);
            throw new IllegalStateException("Couldn't resolve the current request");
        }

        ServiceClient client = resolveClient(request) ;
        if (client==null) {
            sendError("username / password / IP", INVALID_CREDENTIALS);
            throw new InvalidCredentialsException();
        }
        LoggedUser.init(client, request.getRemoteAddr(), null);

        // Authentication succeeded
        Collection<SimpleGrantedAuthority> authority = Collections.singleton(new SimpleGrantedAuthority("ROLE_REST"));
        return new UsernamePasswordAuthenticationToken(principal, credentials, authority);
    }

    public void setAccessServiceLocal(final AccessServiceLocal accessService) {
        this.accessService = accessService;
    }

    public void setChannelServiceLocal(final ChannelServiceLocal channelService) {
        this.channelService = channelService;
    }

    public void setElementServiceLocal(final ElementServiceLocal elementService) {
        this.elementService = elementService;
    }
    private ServiceClient resolveClient(final HttpServletRequest request) {
        final String address = request.getRemoteAddr();
        String[] credentials = WebServiceHelper.getCredentials(request);
        if (credentials == null) {
            credentials = BLANK_CREDENTIALS;
        }
        try {
            return serviceClientServiceLocal.findByAddressAndCredentials(address, credentials[0], credentials[1]);
        } catch (final EntityNotFoundException e) {
            return null;
        }
    }
    @Override
    public boolean supports(final Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private void sendError(final String message, final String errorCode) {
        HttpServletResponse response = WebServiceContext.getResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(response.getWriter(), new ServerErrorVO(errorCode, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

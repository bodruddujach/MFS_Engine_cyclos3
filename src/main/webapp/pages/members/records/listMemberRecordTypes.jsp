<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags/struts-html" prefix="html" %>
<%@ taglib uri="http://sslext.sf.net/tags/sslext" prefix="ssl" %>
<%@ taglib uri="http://devel.cyclos.org/tlibs/cyclos-core" prefix="cyclos" %>
<%@ taglib uri="http://www.servletsuite.com/servlets/toggletag" prefix="t" %> 

<cyclos:script src="/pages/members/records/listMemberRecordTypes.js" />

<script>
	var removeConfirmation = "<cyclos:escapeJS><bean:message key='memberRecordType.removeConfirmation'/></cyclos:escapeJS>";
</script>

<table class="defaultTableContent" cellspacing="0" cellpadding="0">
    <tr>
        <td class="tdHeaderTable"><bean:message key='memberRecordType.title.list'/></td>
        <cyclos:help page="member_records#member_record_types_list"/>
    </tr>
    <tr>
        <td colspan="2" align="left" class="tdContentTableLists">
            <table class="defaultTable">
                <tr>
                    <th class="tdHeaderContents" width="90%"><bean:message key='memberRecordType.name'/></th>
                    <th class="tdHeaderContents" width="10%">&nbsp;</th>
                </tr>                
				<c:forEach var="memberRecordType" items="${memberRecordTypes}">
	                <tr class="<t:toggle>ClassColor1|ClassColor2</t:toggle>">
	                    <td align="left">${memberRecordType.name}</td>
	                    <td align="center" nowrap="nowrap">
	                    	<c:choose><c:when test="${editable}">
	                    	<img memberRecordTypeId="${memberRecordType.id}" src="<c:url value="/pages/images/edit.gif" />" class="edit details" />
	                    	<img memberRecordTypeId="${memberRecordType.id}" src="<c:url value="/pages/images/delete.gif" />" class="remove" />
		                    </c:when><c:otherwise>
		                    	<img memberRecordTypeId="${memberRecordType.id}" src="<c:url value="/pages/images/view.gif" />" class="view details" />
		                    </c:otherwise></c:choose>		                    	
						</td>
					</tr>
				</c:forEach>                
            </table>
        </td>
    </tr>
</table>
<c:if test="${editable}">

	<table class="defaultTableContentHidden" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<span class="label"><bean:message key='memberRecordType.action.new'/></span>
				<input class="button" type="button" id="newButton" value="<bean:message key='global.submit'/>">
			</td>
		</tr>
	</table>
</c:if>	
//package nl.strohalm.cyclos.mfs.controllers;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.Predicate;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import nl.strohalm.cyclos.entities.access.Channel;
//import nl.strohalm.cyclos.entities.accounts.AccountOwner;
//import nl.strohalm.cyclos.entities.accounts.AccountStatus;
//import nl.strohalm.cyclos.entities.accounts.LockedAccountsOnPayments;
//import nl.strohalm.cyclos.entities.accounts.SystemAccountOwner;
//import nl.strohalm.cyclos.entities.accounts.transactions.Transfer;
//import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
//import nl.strohalm.cyclos.entities.customization.fields.MemberCustomField;
//import nl.strohalm.cyclos.entities.customization.fields.MemberCustomFieldValue;
//import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
//import nl.strohalm.cyclos.entities.groups.MemberGroup;
//import nl.strohalm.cyclos.entities.members.Element;
//import nl.strohalm.cyclos.entities.members.Member;
//import nl.strohalm.cyclos.entities.services.ServiceClient;
//import nl.strohalm.cyclos.entities.services.ServiceOperation;
//import nl.strohalm.cyclos.exceptions.PermissionDeniedException;
//import nl.strohalm.cyclos.services.access.AccessServiceLocal;
//import nl.strohalm.cyclos.services.access.exceptions.BlockedCredentialsException;
//import nl.strohalm.cyclos.services.access.exceptions.InvalidCredentialsException;
//import nl.strohalm.cyclos.services.accounts.AccountDTO;
//import nl.strohalm.cyclos.services.accounts.AccountServiceLocal;
//import nl.strohalm.cyclos.services.application.ApplicationServiceLocal;
//import nl.strohalm.cyclos.services.customization.MemberCustomFieldServiceLocal;
//import nl.strohalm.cyclos.services.elements.ElementServiceLocal;
//import nl.strohalm.cyclos.services.services.ServiceClientServiceLocal;
//import nl.strohalm.cyclos.services.transactions.DoPaymentDTO;
//import nl.strohalm.cyclos.services.transactions.PaymentServiceLocal;
//import nl.strohalm.cyclos.services.transactions.TicketServiceLocal;
//import nl.strohalm.cyclos.services.transactions.TransferAuthorizationServiceLocal;
//import nl.strohalm.cyclos.webservices.WebServiceContext;
//import nl.strohalm.cyclos.webservices.model.AccountHistoryTransferVO;
//import nl.strohalm.cyclos.webservices.model.AccountStatusVO;
//import nl.strohalm.cyclos.webservices.payments.PaymentParameters;
//import nl.strohalm.cyclos.webservices.payments.PaymentResult;
//import nl.strohalm.cyclos.webservices.payments.PaymentStatus;
//import nl.strohalm.cyclos.webservices.rest.BaseRestController;
//import nl.strohalm.cyclos.webservices.utils.AccountHelper;
//import nl.strohalm.cyclos.webservices.utils.ChannelHelper;
//import nl.strohalm.cyclos.webservices.utils.MemberHelper;
//import nl.strohalm.cyclos.webservices.utils.PaymentHelper;
//import nl.strohalm.cyclos.webservices.utils.WebServiceHelper;
//
//@Controller
//@RequestMapping("/v1/api/txn")
//public class TxnController2 extends BaseRestController {
//
//	@Autowired
//	private AccountServiceLocal accountServiceLocal;
//	@Autowired
//	private AccessServiceLocal accessServiceLocal;
//	@Autowired
//	private ApplicationServiceLocal applicationServiceLocal;
//	@Autowired
//	private PaymentServiceLocal paymentServiceLocal;
//	@Autowired
//	private TicketServiceLocal ticketServiceLocal;
//	@Autowired
//	private ElementServiceLocal elementServiceLocal;
//	@Autowired
//	private MemberCustomFieldServiceLocal memberCustomFieldServiceLocal;
//	@Autowired
//	private ServiceClientServiceLocal serviceClientServiceLocal;
//	@Autowired
//	private TransferAuthorizationServiceLocal transferAuthorizationServiceLocal;
//	@Autowired
//	private PaymentHelper paymentHelper;
//	@Autowired
//	private MemberHelper memberHelper;
//	@Autowired
//	private WebServiceHelper webServiceHelper;
//	@Autowired
//	private AccountHelper accountHelper;
//	@Autowired
//	private ChannelHelper channelHelper;
//
//	public PaymentResult doPayment(final PaymentParameters params) {
//		AccountHistoryTransferVO transferVO = null;
//		Transfer transfer = null;
//		PaymentStatus status = null;
//		AccountStatusVO fromMemberStatus = null;
//		AccountStatusVO toMemberStatus = null;
//		try {
//			final PrepareParametersResult result = prepareParameters(params);
//			status = result.getStatus();
//
//			if (status == null) {
//				// Status null means no "pre-payment" errors (like validation, pin, channel...)
//				// Perform the payment
//				final DoPaymentDTO dto = paymentHelper.toExternalPaymentDTO(params, result.getFrom(), result.getTo());
//
//				// Validate the transfer type
//				if (!validateTransferType(dto)) {
//					status = PaymentStatus.INVALID_PARAMETERS;
//					webServiceHelper.trace(status
//							+ ". Reason: The service client doesn't have permission to the specified transfer type: "
//							+ dto.getTransferType());
//				} else {
//					transfer = (Transfer) paymentServiceLocal.doPayment(dto);
//					status = paymentHelper.toStatus(transfer);
//					transferVO = accountHelper.toVO(WebServiceContext.getMember(), transfer, null,
//							result.getFromRequiredFields(), result.getToRequiredFields());
//					final AccountStatusVO[] statuses = getAccountStatusesForPayment(params, transfer);
//					fromMemberStatus = statuses[0];
//					toMemberStatus = statuses[1];
//				}
//			}
//		} catch (final Exception e) {
//			webServiceHelper.error(e);
//			if (applicationServiceLocal.getLockedAccountsOnPayments() == LockedAccountsOnPayments.NONE) {
//				// The payment is committed on the same transaction so it will be rolled back by
//				// this exception, then status is given by this
//				// exception.
//				status = paymentHelper.toStatus(e);
//			} else if (status == null) {
//				/*
//				 * The payment is committed on a new transaction. So this exception won't roll
//				 * back the payment. The status sholuldn't be modified by this exception unless
//				 * there isn't a status, in which case return this exception.
//				 */
//				status = paymentHelper.toStatus(e);
//			}
//		}
//
//		if (!status.isSuccessful()) {
//			webServiceHelper.error("Payment error status: " + status);
//		}
//
//		return new PaymentResult(status, transferVO, fromMemberStatus, toMemberStatus);
//	}
//
//	/**
//	 * Checks the given member's pin
//	 */
//	private void checkCredentials(Member member, final Channel channel, final String credentials) {
//		if (member == null) {
//			return;
//		}
//		final ServiceClient client = WebServiceContext.getClient();
//		final Member restrictedMember = client.getMember();
//		if (restrictedMember == null) {
//			// Non-restricted clients use the flag credentials required
//			if (!client.isCredentialsRequired()) {
//				// No credentials should be checked
//				throw new InvalidCredentialsException();
//			}
//		} else {
//			// Restricted clients don't need check if is the same member
//			if (restrictedMember.equals(member)) {
//				throw new InvalidCredentialsException();
//			}
//		}
//		if (StringUtils.isEmpty(credentials)) {
//			throw new InvalidCredentialsException();
//		}
//		member = elementServiceLocal.load(member.getId(), Element.Relationships.USER);
//		accessServiceLocal.checkCredentials(channel, member.getMemberUser(), credentials,
//				WebServiceContext.getRequest().getRemoteAddr(), WebServiceContext.getMember());
//	}
//
//	/**
//	 * Returns the account status for the from account and to account, if the given
//	 * {@link PaymentParameters#isReturnStatus()} is true and according to the
//	 * current invocation permissions
//	 */
//	private AccountStatusVO[] getAccountStatusesForPayment(final PaymentParameters params, final Transfer transfer) {
//		AccountStatus fromMemberStatus = null;
//		AccountStatus toMemberStatus = null;
//		if (WebServiceContext.getClient().getPermissions().contains(ServiceOperation.ACCOUNT_DETAILS)
//				&& params.getReturnStatus()) {
//			if (WebServiceContext.getMember() == null) {
//				fromMemberStatus = transfer.isFromSystem() ? null
//						: accountServiceLocal.getCurrentStatus(new AccountDTO(transfer.getFrom()));
//				toMemberStatus = transfer.isToSystem() ? null
//						: accountServiceLocal.getCurrentStatus(new AccountDTO(transfer.getTo()));
//			} else if (WebServiceContext.getMember().equals(paymentHelper.resolveFromMember(params))) {
//				fromMemberStatus = transfer.isFromSystem() ? null
//						: accountServiceLocal.getCurrentStatus(new AccountDTO(transfer.getFrom()));
//			} else {
//				toMemberStatus = transfer.isToSystem() ? null
//						: accountServiceLocal.getCurrentStatus(new AccountDTO(transfer.getTo()));
//			}
//		}
//		return new AccountStatusVO[] { accountHelper.toVO(fromMemberStatus), accountHelper.toVO(toMemberStatus) };
//	}
//
//	private Collection<MemberCustomField> getMemberCustomFields(final Member member,
//			final List<String> fieldInternalNames) {
//		Collection<MemberCustomField> fields = new HashSet<MemberCustomField>();
//
//		for (final String internalName : fieldInternalNames) {
//			MemberCustomFieldValue mcfv = (MemberCustomFieldValue) CollectionUtils.find(member.getCustomValues(),
//					new Predicate() {
//						@Override
//						public boolean evaluate(final Object object) {
//							MemberCustomFieldValue mcfv = (MemberCustomFieldValue) object;
//							return mcfv.getField().getInternalName().equals(internalName);
//						}
//					});
//			if (mcfv == null) {
//				webServiceHelper.trace(
//						String.format("Required field '%1$s' was not found for member %2$s", internalName, member));
//				return null;
//			} else {
//				fields.add(memberCustomFieldServiceLocal.load(mcfv.getField().getId()));
//			}
//		}
//
//		return fields;
//	}
//
//	/**
//	 * Prepares the parameters for a payment. The resulting status is null when no
//	 * problem found
//	 */
//	private PrepareParametersResult prepareParameters(final PaymentParameters params) {
//
//		final Member restricted = WebServiceContext.getMember();
//		final boolean fromSystem = params.getFromSystem();
//		final boolean toSystem = params.getToSystem();
//		PaymentStatus status = null;
//		Member fromMember = null;
//		Member toMember = null;
//		// Load the from member
//		if (!fromSystem) {
//			try {
//				fromMember = paymentHelper.resolveFromMember(params);
//			} catch (final EntityNotFoundException e) {
//				webServiceHelper.error(e);
//				status = PaymentStatus.FROM_NOT_FOUND;
//			}
//		}
//		// Load the to member
//		if (status == null && !toSystem) {
//			try {
//				toMember = paymentHelper.resolveToMember(params);
//			} catch (final EntityNotFoundException e) {
//				webServiceHelper.error(e);
//				status = PaymentStatus.TO_NOT_FOUND;
//			}
//		}
//
//		if (status == null) {
//			if (restricted == null) {
//				// Ensure has the do payment permission
//				if (!WebServiceContext.hasPermission(ServiceOperation.DO_PAYMENT)) {
//					throw new PermissionDeniedException(
//							"The service client doesn't have the following permission: " + ServiceOperation.DO_PAYMENT);
//				}
//				// Check the channel immediately, as needed by SMS controller
//				if (fromMember != null
//						&& !accessServiceLocal.isChannelEnabledForMember(channelHelper.restricted(), fromMember)) {
//					status = PaymentStatus.INVALID_CHANNEL;
//				}
//			} else {
//				// Enforce the restricted to member parameters
//				if (fromSystem) {
//					// Restricted to member can't perform payment from system
//					status = PaymentStatus.FROM_NOT_FOUND;
//				} else {
//					if (fromMember == null) {
//						fromMember = restricted;
//					} else if (toMember == null && !toSystem) {
//						toMember = restricted;
//					}
//				}
//				if (status == null) {
//					// Check make / receive payment permissions
//					if (fromMember.equals(restricted)) {
//						if (!WebServiceContext.hasPermission(ServiceOperation.DO_PAYMENT)) {
//							throw new PermissionDeniedException(
//									"The service client doesn't have the following permission: "
//											+ ServiceOperation.DO_PAYMENT);
//						}
//					} else {
//						if (!WebServiceContext.hasPermission(ServiceOperation.RECEIVE_PAYMENT)) {
//							throw new PermissionDeniedException(
//									"The service client doesn't have the following permission: "
//											+ ServiceOperation.RECEIVE_PAYMENT);
//						}
//					}
//					// Ensure that either from or to member is the restricted one
//					if (!fromMember.equals(restricted) && !toMember.equals(restricted)) {
//						status = PaymentStatus.INVALID_PARAMETERS;
//						webServiceHelper.trace(status
//								+ ". Reason: Neither the origin nor the destination members are equal to the restricted: "
//								+ restricted);
//					}
//				}
//				if (status == null) {
//					// Enforce the permissions
//					if (restricted.equals(fromMember)
//							&& !WebServiceContext.hasPermission(ServiceOperation.DO_PAYMENT)) {
//						throw new PermissionDeniedException("The service client doesn't have the following permission: "
//								+ ServiceOperation.DO_PAYMENT);
//					} else if (restricted.equals(toMember)
//							&& !WebServiceContext.hasPermission(ServiceOperation.RECEIVE_PAYMENT)) {
//						throw new PermissionDeniedException("The service client doesn't have the following permission: "
//								+ ServiceOperation.RECEIVE_PAYMENT);
//					}
//				}
//			}
//		}
//
//		// Ensure both from and to member are present
//		if (status == null) {
//			if (fromMember == null && !fromSystem) {
//				status = PaymentStatus.FROM_NOT_FOUND;
//			} else if (toMember == null && !toSystem) {
//				status = PaymentStatus.TO_NOT_FOUND;
//			} else if (fromMember != null && toMember != null) {
//				// Ensure the to member is visible by the from member
//				final Collection<MemberGroup> visibleGroups = fromMember.getMemberGroup().getCanViewProfileOfGroups();
//				if (CollectionUtils.isEmpty(visibleGroups) || !visibleGroups.contains(toMember.getGroup())) {
//					status = PaymentStatus.TO_NOT_FOUND;
//				}
//			}
//		}
//
//		// Ensure required CF are present ONLY for unrestricted client
//		Collection<MemberCustomField> fromMemberfields = null, toMemberfields = null;
//		if (status == null) {
//			boolean hasFromRequired = CollectionUtils.isNotEmpty(params.getFromMemberFieldsToReturn());
//			boolean hasToRequired = CollectionUtils.isNotEmpty(params.getToMemberFieldsToReturn());
//			if (restricted != null && (hasFromRequired || hasToRequired) || hasFromRequired && fromSystem
//					|| hasToRequired && toSystem) {
//				webServiceHelper.trace(restricted != null
//						? "Restricted web service clients are not allowed to require member custom field values"
//						: "Can't require custom field values for a system payment");
//				status = PaymentStatus.INVALID_PARAMETERS;
//			}
//			if (status == null && hasFromRequired) {
//				fromMemberfields = getMemberCustomFields(fromMember, params.getFromMemberFieldsToReturn());
//				status = fromMemberfields == null ? PaymentStatus.INVALID_PARAMETERS : null;
//			}
//
//			if (status == null && hasToRequired) {
//				toMemberfields = getMemberCustomFields(toMember, params.getToMemberFieldsToReturn());
//				status = toMemberfields == null ? PaymentStatus.INVALID_PARAMETERS : null;
//			}
//		}
//
//		if (status == null) {
//			// Check the channel
//			if (fromMember != null
//					&& !accessServiceLocal.isChannelEnabledForMember(channelHelper.restricted(), fromMember)) {
//				status = PaymentStatus.INVALID_CHANNEL;
//			}
//		}
//		if (status == null) {
//			// Check the credentials
//			boolean checkCredentials;
//			if (restricted != null) {
//				checkCredentials = !fromMember.equals(restricted);
//			} else {
//				checkCredentials = !fromSystem && WebServiceContext.getClient().isCredentialsRequired();
//			}
//			if (checkCredentials) {
//				try {
//					checkCredentials(fromMember, WebServiceContext.getChannel(), params.getCredentials());
//				} catch (final InvalidCredentialsException e) {
//					webServiceHelper.error(e);
//					status = PaymentStatus.INVALID_CREDENTIALS;
//				} catch (final BlockedCredentialsException e) {
//					webServiceHelper.error(e);
//					status = PaymentStatus.BLOCKED_CREDENTIALS;
//				}
//			}
//		}
//
//		// No error
//		final AccountOwner fromOwner = fromSystem ? SystemAccountOwner.instance() : fromMember;
//		final AccountOwner toOwner = toSystem ? SystemAccountOwner.instance() : toMember;
//		return new PrepareParametersResult(status, fromOwner, toOwner, fromMemberfields, toMemberfields);
//	}
//
//	private boolean validateTransferType(final DoPaymentDTO dto) {
//		final Collection<TransferType> possibleTypes = paymentHelper.listPossibleTypes(dto);
//		return possibleTypes != null && possibleTypes.contains(dto.getTransferType());
//	}
//
//	private static class PrepareParametersResult {
//		private final PaymentStatus status;
//		private final AccountOwner from;
//		private final AccountOwner to;
//		private final Collection<MemberCustomField> fromRequiredFields;
//		private final Collection<MemberCustomField> toRequiredFields;
//
//		public PrepareParametersResult(final PaymentStatus status, final AccountOwner from, final AccountOwner to,
//				final Collection<MemberCustomField> fromRequiredFields,
//				final Collection<MemberCustomField> toRequiredFields) {
//			this.status = status;
//			this.from = from;
//			this.to = to;
//			this.fromRequiredFields = fromRequiredFields;
//			this.toRequiredFields = toRequiredFields;
//		}
//
//		public AccountOwner getFrom() {
//			return from;
//		}
//
//		public Collection<MemberCustomField> getFromRequiredFields() {
//			return fromRequiredFields;
//		}
//
//		public PaymentStatus getStatus() {
//			return status;
//		}
//
//		public AccountOwner getTo() {
//			return to;
//		}
//
//		public Collection<MemberCustomField> getToRequiredFields() {
//			return toRequiredFields;
//		}
//	}
//
//}

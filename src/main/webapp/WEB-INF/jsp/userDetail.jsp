<%--
    ----------------------------------------------------------------------
    Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
    ----------------------------------------------------------------------
     Log of Changes:
 
      Date            Author                  Description
      -------         ---------               -------------
      2008/02/02     (Krishna M. Kunapuli)(V) Initial Version
     ---------------------------------------------------------------------- 
 --%>
<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/main.css" rel="stylesheet" type="text/css">
<html>
	<head>
		<title>User Details</title>
	</head>

	<body>
		<h2>
			User Details
		</h2>
		<form:form commandName="user" method="POST" action="registerUser.htm">
			<input type="hidden" name="id" value="${user.id}" />
			<%
				int completedPledges = 0;
				int activePledges = 0;
				int disabledPledges = 0;
				int paymentsReceived = 0;
				int paymentsPending = 0;
				double totalPledgedAmount = 0d;
				double totalAmountPaid = 0d;
				com.starpath.domain.User user = (com.starpath.domain.User) request
						.getAttribute("user");
				java.util.Set pledges = user.getPledges();
				if (pledges != null && pledges.size() > 0) {
					java.util.Iterator itr = pledges.iterator();
					while (itr.hasNext()) {
						com.starpath.domain.Pledge pledge = (com.starpath.domain.Pledge) itr
						.next();
						if (pledge.getPaymentStatus().equalsIgnoreCase("ACTIVE")) {
					activePledges = activePledges + 1;
						} else if (pledge.getPaymentStatus().equalsIgnoreCase(
						"INACTIVE")) {
					disabledPledges = disabledPledges + 1;
						} else {
					completedPledges = completedPledges + 1;
						}
						java.util.Set paymentDetails = pledge.getPaymentDetails();
						if (paymentDetails != null && paymentDetails.size() > 0) {
					java.util.Iterator itr1 = paymentDetails.iterator();
					while (itr1.hasNext()) {
						com.starpath.domain.PaymentDetail paymentDetail = (com.starpath.domain.PaymentDetail) itr1
						.next();
						if (paymentDetail.getPaymentReceived() == true) {
							paymentsReceived = paymentsReceived + 1;
							totalAmountPaid = totalAmountPaid + paymentDetail.getPaymentAmount();
						} else {
							paymentsPending = paymentsPending + 1;
						}

					}
						}
					totalPledgedAmount = totalPledgedAmount + pledge.getPledgeAmount();
					}
				}
			%>
			<table border="0" cellspacing="5" cellpadding="0">
				<tr>
					<td class="sh_1">
						Email :
					</td>
					<td align="left">
						<c:out value="${user.email}" />
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						First Name :
					</td>
					<td align="left">
						<c:out value="${user.firstName}" />
					</td>
				</tr>

				<tr>
					<td class="sh_1">
						Last Name :
					</td>
					<td valign="top" align="left">
						<c:out value="${user.lastName}" />
					</td>
				</tr>
				
				<tr>
					<td class="sh_1">
						Phone Number :
					</td>
					<td valign="middle" align="left">
						<c:out value="${user.phoneNumber}" />
					</td>
				</tr>

				<tr>
					<td class="sh_1">
						Address :
					</td>
					<td valign="middle" align="left">
						<c:out value="${user.address.address}" />
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						City :
					</td>
					<td>
						<c:out value="${user.address.city}" />

					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Zipcode :
					</td>
					<td>
						<c:out value="${user.address.postalCode}" />

					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Active Pledges :
					</td>
					<td>
						<%=activePledges%>

					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Completed Pledges :
					</td>
					<td>
						<%=completedPledges%>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Disabled Pledges :
					</td>
					<td>
						<%=disabledPledges%>

					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Payments Received :
					</td>
					<td>
						<%=paymentsReceived%>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Payments Pending :
					</td>
					<td>
						<%=paymentsPending%>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Total Pledge Amount :
					</td>
					<td>
						<%=totalPledgedAmount%>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Total Amount Paid :
					</td>
					<td>
						<%=totalAmountPaid%>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						<b><a href="editUser.htm?&id=${user.id}">Edit User</a></b>
					</td>
					<td>
						<b><a href="addPledge.htm?&userid=${user.id}">Add Pledge</a></b>
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>

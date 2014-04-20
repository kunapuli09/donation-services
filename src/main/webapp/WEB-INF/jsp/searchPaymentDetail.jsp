<%--
    ----------------------------------------------------------------------
    Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
    ----------------------------------------------------------------------
     Log of Changes:
 
      Date            Author                  Description
      -------         ---------               -------------
      2008/02/02     (Swetha Kunapuli)(V) Initial Version
     ---------------------------------------------------------------------- 
 --%>
<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<link href="css/main.css" rel="stylesheet" type="text/css">
<div class="sh">
	<tr>
		<td colspan="4" class="rs" />
	</tr>

	<tr colspan="4">
		<td align="left">
			Search Payment Details
		</td>
	</tr>
</div>
<form method="post" action="searchPaymentDetailAction.htm">
	<div class="sh_1">
		<tr>
			<td colspan="4" class="rs" />
		</tr>
<authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
		<tr>
			<td>
				Search Payment Details by User's Last Name :
			</td>
			<td>
				<input type="text" name="lastName" />
			</td>
			<td>
				<input type="submit" name="Search" value="Search" />
			</td>
		</tr>
</authz:authorize>
		<logic:present name="paymentDetails">
			<display:table name="paymentDetails" class="its" export="true" 
				sort="list" pagesize="10" requestURI="">
		<display:column property="pledge.user.lastName" sortable="true" title="Last Name"
					headerClass="sortable" maxWords="1"/>
			        <display:column property="pledge.user.firstName" sortable="true" title="First Name"
									headerClass="sortable" maxWords="1"/>
		
				<display:column property="account" sortable="true" title="Account No."
					headerClass="sortable" />
				<display:column property="bankname" sortable="true" title="Bank"
					headerClass="sortable" maxWords="1"/>
				<display:column property="swift" sortable="true" title="Swift"
					headerClass="sortable" maxWords="1"/>
				<display:column property="paymentDate" format="{0,date,dd-MMM-yyyy}" sortable="true"
					title="Payment Date" headerClass="sortable" />
				<display:column property="paymentAmount" sortable="true"
					title="Amount" headerClass="sortable" />
				<display:column property="paymentReceived" sortable="true"
					title="Paid" headerClass="sortable" maxWords="1"/>

				<%--
    <display:column property="type" sortable="true" title="type"  headerClass="sortable" />
    <display:column property="number" sortable="true" title="number" headerClass="sortable"/>
    <display:column property="expMonth" sortable="true" title="expMonth" headerClass="sortable"/>
    <display:column property="expYear" sortable="true" title="expYear" headerClass="sortable"/>
	--%>
			 <display:column property="id" title="Payment" media="html" headerClass="sortable" sortable="true" decorator="com.starpath.mvc.PaymentDetailDecorator" />
			</display:table>
		</logic:present>
	</div>
</form>

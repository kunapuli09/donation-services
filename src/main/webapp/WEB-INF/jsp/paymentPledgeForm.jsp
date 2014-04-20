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
<%@ page contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/main.css" rel="stylesheet" type="text/css">
<html>
	<head>
		<title>Add Pledge</title>
	</head>
	<body>
		<script type="text/javascript"><%--
function onPayModediv(mode)
{
	
	if(mode.value == "cheque")	
	{
		document.all.Chequediv.style.display = "block";
		document.all.Creditdiv.style.display = "none";
		document.all.Nonediv.style.display = "none";
	}
	else if(mode.value == "creditcard")	
	{
		document.all.Chequediv.style.display = "none";
		document.all.Creditdiv.style.display = "block";
		document.all.Nonediv.style.display = "none";
	}
	else if(mode.value == "none")	
	{
		document.all.Chequediv.style.display = "none";
		document.all.Creditdiv.style.display = "none";
		document.all.Nonediv.style.display = "block";
	}

}
function onPldgType(node)
{
	if(node.value == "TERM")
	{
		document.all.freqTerm.disabled = false;
		document.all.freqType.disabled = false;	
	}
	else
	{
		document.all.freqTerm.disabled = true;
		document.all.freqType.disabled = true;
	}
}

--%></script>


		<form:form method="POST" action="registerUser.htm"
			commandName="paymentDetail">
			<input type="hidden" name="page" value="1" />
			<br />
			<input type="hidden" value="<c:out value="${nextPledge}" />" />
			<br />
			<div class="sh">
				<table height="25px;" width="100%">
			<tr colspan="4">
				<td style="align: left; background: #ffff99; text-align: center;">
					<strong>User Information</strong>
				</td>
				<td style="align: left; background: #F4BF3E; text-align: center;">
					Pledge
				</td>
				<td style="align: left; background: #ffff99; text-align: center;">
					Confirmation
				</td>
				<td style="align: left; background: #ffffff; text-align: center;"></td>
			</tr>
		</table>
			</div>
			<div>
				<tr>
					<td colspan="4" class="rs">
				</tr>
			</div>
			<table cellpadding="0" cellspacing="0" align="center">
<tr>
			<td colspan="4" class="rs">
		</tr>
				<tr>
					<td class="sh_1">
						Total Pledge Amount :
					</td>
					<td>
						<form:input path="pledge.pledgeAmount" />
						<form:errors path="pledge.pledgeAmount" cssClass="error"></form:errors>
					</td>


				</tr>
				<tr>
					<td class="sh_1">
						Number of Payment Terms:
					</td>
					<td>
						<rr:frequencyTerms path="pledge.paymentFrequency" />
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Comment :
					</td>
					<td>
						<form:textarea path="pledge.comment" cols="16"/>
					</td>
				</tr>
				<tr>
					<td class="sh_2">
						Please fill in the payment information to pay now
				</td>
				</tr>
				<tr>
					<td class="sh_2">
						OR click NEXT to finish your pledge.
					</td>
				</tr>
<tr>
			<td colspan="4" class="rs">
		</tr>

				<tr>
					<td class="sh_1">
						Bank Name:
					</td>
					<td>
						<form:input path="bankname" />
					<td>
				</tr>
				<tr>
					<td class="sh_1">
						Bank Swift:
					</td>
					<td>
						<form:input path="swift" />
					<td>
				</tr>
				<tr>
					<td class="sh_1">
						Bank Account:
					</td>
					<td>
						<form:input path="account" />
					<td>
				</tr>
				<tr>
					<td class="sh_1">
						Check Number:
					</td>
					<td>
						<form:input path="checkNumber" />
					<td>
						<form:errors path="checkNumber" cssClass="error"></form:errors>
				</tr>
				<tr>
					<td class="sh_1">
						Payment Amount:
					</td>
					<td>
						<form:input path="paymentAmount" />
					<td>
						<form:errors path="paymentAmount" cssClass="error"></form:errors>
				</tr>
<tr>
			<td colspan="4" class="rs">
		</tr>


				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="_target2" value="Next">
						&nbsp;
						<input type="submit" name="_target0" value="Back">
						&nbsp;
						<input type="submit" name="_cancel" value="Cancel">
						
						
					</td>
				<tr>
			</table>
		</form:form>
	</body>
</html>

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
<link href="css/main.css" rel="stylesheet" type="text/css">
<head>
</head>

<body>
<form:form method="POST" action="registerUser.htm"
	onsubmit="return checkEmail()" commandName="paymentDetail">
	<input type="hidden" name="page" value="0" />
	<br>
	<div class="sh"   >
	<table height="25px;" width="100%">
			<tr colspan="4">
				<td style="align: left; background: #F4BF3E; text-align: center;">
					<strong>User Information</strong>
				</td>
				<td style="align: left; background: #ffff99; text-align: center;">
					Pledge
				</td>
				<td style="align: left; background: #ffff99; text-align: center;">
					Confirmation
				</td>
				<td style="align: left; background: #ffffff; text-align: center;"></td>
			</tr>
		</table>
	</div>
	<table cellpadding="0" cellspacing="0" align="center">

		<tr>
			<td colspan="4" class="rs"></td>
		</tr>
		<tr>
			<td class="sh_1">
				Phone Number :*
			</td>
			<td>
				<form:input path="pledge.user.phoneNumber"
					onkeypress="checkNum(this)" maxlength="12" />
				<form:errors path="pledge.user.phoneNumber" cssClass="error"></form:errors>
				Ex:xxx-xxx-xxxx
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Email :*
			</td>
			<td>
				<form:input path="pledge.user.email" id="emailid" />
				<form:errors path="pledge.user.email" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Password :*
			</td>
			<td>
				<form:password path="pledge.user.password" id="password" />
				<form:errors path="pledge.user.password" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Confirm Password :*
			</td>
			<td>
				<form:password path="pledge.user.confirmPassword"
					id="confirmpassword" onblur="onPasswordMismatch()" /><div  id="pword" style="display:none" class="sh_2"><font color="red">Password and Confirm Password should be same</font></div>
				<form:errors path="pledge.user.confirmPassword" cssClass="error"></form:errors>
				
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				First Name :*
			</td>
			<td>
				<form:input path="pledge.user.firstName" />
				<form:errors path="pledge.user.firstName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Last Name :*
			</td>
			<td>
				<form:input path="pledge.user.lastName" />
				<form:errors path="pledge.user.lastName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Address :
			</td>
			<td>
				<form:input path="pledge.user.address.address" />
				<form:errors path="pledge.user.address.address" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				City :
			</td>
			<td>
				<form:input path="pledge.user.address.city" />
				<form:errors path="pledge.user.address.city" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				State :
			</td>
			<td>
				<form:input maxlength="2" path="pledge.user.address.province" />
				<form:errors path="pledge.user.address.province" cssClass="error"></form:errors>
			</td>
		</tr>	
		
		<tr>
			<td class="sh_1">
				Zipcode :
			</td>
			<td>
				<form:input path="pledge.user.address.postalCode" />
				<form:errors path="pledge.user.address.postalCode" cssClass="error"></form:errors>
			</td>
		</tr>
		<%--<tr>
			<td class="sh_1">
				Website :
			</td>
			<td>
				<form:input path="website" />
			</td>
		</tr>	
		--%>
		<%--<tr>
			<td>
				enabled
			</td>
			<td>
				<form:checkbox path="enabled" />
			</td>
		</tr>	
		<tr>
			<td>
				accountExpired
			</td>
			<td>
				<form:checkbox path="accountExpired" />
			</td>
		</tr>	
		<tr>
			<td>
				accountLocked
			</td>
			<td>
				<form:checkbox path="accountLocked" />
			</td>
		</tr>	
		<tr>
			<td>
				credentialsExpired
			</td>
			<td>
				<form:checkbox path="credentialsExpired" />
			</td>
		</tr>
		--%>
		<tr>
			<td colspan="4" class="rs"></td>
		</tr>

		<tr>
			<td colspan="4" class="rs"></td>
		</tr>

		<tr>
			<td colspan="4" class="rs"></td>
		</tr>

		<tr colspan="2" align="right">
			<td>
				<input type="submit" name="_target1" value="Next" />
				&nbsp;
				<input type="submit" name="_cancel" value="Cancel" />
			</td>
		</tr>
	</table>
</form:form>
</body>


<%@ page contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<link href="css/css-content.css" rel="stylesheet" type="text/css">
<head>
</head>

<body>
<form:form method="POST" action="registerUserMembers.htm"
	onsubmit="return checkEmail()" commandName="user">
	<input type="hidden" name="page" value="0" />
	<br>
	<div class="sh"   >
	<table height="25px;" width="100%">
			<tr colspan="4">
				<td style="align: left; background: #F4BF3E; text-align: center;">
					<strong>User Information</strong>
				</td>
				<td style="align: left; background: #ffff99; text-align: center;">
					Family Member Information
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
				<form:input path="phoneNumber"
					onkeypress="checkNum(this)" maxlength="12" />
				<form:errors path="phoneNumber" cssClass="error"></form:errors>
				Ex:xxx-xxx-xxxx
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Email :*
			</td>
			<td>
				<form:input path="email" id="emailid" />
				<form:errors path="email" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Password :*
			</td>
			<td>
				<form:password path="password" id="password" />
				<form:errors path="password" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Confirm Password :*
			</td>
			<td>
				<form:password path="confirmPassword"
					id="confirmpassword" onblur="onPasswordMismatch()" /><div  id="pword" style="display:none" class="sh_2"><font color="red">Password and Confirm Password should be same</font></div>
				<form:errors path="confirmPassword" cssClass="error"></form:errors>
				
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				First Name :*
			</td>
			<td>
				<form:input path="firstName" />
				<form:errors path="firstName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Last Name :*
			</td>
			<td>
				<form:input path="lastName" />
				<form:errors path="lastName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Address :
			</td>
			<td>
				<form:input path="address.address" />
				<form:errors path="address.address" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				City :
			</td>
			<td>
				<form:input path="address.city" />
				<form:errors path="address.city" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				State :
			</td>
			<td>
				<form:input maxlength="2" path="address.province" />
				<form:errors path="address.province" cssClass="error"></form:errors>
			</td>
		</tr>	
		
		<tr>
			<td class="sh_1">
				Zipcode :
			</td>
			<td>
				<form:input path="address.postalCode" maxlength="5"/>
				<form:errors path="address.postalCode" cssClass="error"></form:errors>
			</td>
		</tr>
				
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

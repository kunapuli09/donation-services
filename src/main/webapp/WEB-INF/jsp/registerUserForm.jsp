
<%@ page contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<!--<link href="css/css-content.css" rel="stylesheet" type="text/css">-->
<head>
</head>

<body>
<form:form method="POST" action="registerUserMembers.htm"
	onsubmit="return checkEmail()" commandName="user">
	<input type="hidden" name="page" value="0" />
	<br>
	<div    >
	<table height="25px;" width="100%">
			<tr colspan="4">
				<td>
					<strong>User Information</strong>
				</td>
				
			</tr>
		</table>
	</div>

	<table cellpadding="0" cellspacing="0" align="center">

		<tr>
			<td colspan="4" class="rs"></td>
		</tr>
		<tr>
			<td >
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
			<td >
				Email :*
			</td>
			<td>
				<form:input path="email" id="emailid" />
				<form:errors path="email" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td >
				Password :*
			</td>
			<td>
				<form:password path="password" id="password" />
				<form:errors path="password" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td >
				Confirm Password :*
			</td>
			<td>
				<form:password path="confirmPassword"
					id="confirmpassword" onblur="onPasswordMismatch()" /><div  id="pword" style="display:none" class="sh_2"><font color="red">Password and Confirm Password should be same</font></div>
				<form:errors path="confirmPassword" cssClass="error"></form:errors>
				
			</td>
		</tr>
		<tr>
			<td >
				First Name :*
			</td>
			<td>
				<form:input path="firstName" />
				<form:errors path="firstName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td >
				Last Name :*
			</td>
			<td>
				<form:input path="lastName" />
				<form:errors path="lastName" cssClass="error"></form:errors>

			</td>
		</tr>
		<tr>
			<td >
				Address :
			</td>
			<td>
				<form:input path="address.address" />
				<form:errors path="address.address" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td >
				City :
			</td>
			<td>
				<form:input path="address.city" />
				<form:errors path="address.city" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td >
				State :
			</td>
			<td>
				<form:input maxlength="2" path="address.province" />
				<form:errors path="address.province" cssClass="error"></form:errors>
			</td>
		</tr>	
		
		<tr>
			<td >
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

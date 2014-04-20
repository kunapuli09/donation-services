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
<form:form method="POST" action="addUser.htm" commandName="user">
	<div class="sh">
		<tr align="center">
			<td>
				Add User
			</td>
		</tr>
	</div>
	<div>
		<tr>
			<td colspan="4" class="rs">
		</tr>
	</div>


	<table cellpadding="0" cellspacing="0" align="left">
			<tr>
			<td colspan="4" class="rs">
		</tr>
		<tr>
			<td class="sh_1">
				Phone Number :
			</td>
			<td>
				<form:input path="phoneNumber" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Email :*
			</td>
			<td>
				<form:input path="email" />
				<form:errors path="email" cssClass="error"></form:errors>
				
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Password :*
			</td>
			<td>
				<form:password path="password" />
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Confirm Password :*
			</td>
			<td>
				<form:password path="confirmPassword" />
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				First Name :*
			</td>
			<td>
				<form:input path="firstName" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Last Name :*
			</td>
			<td>
				<form:input path="lastName" />
			</td>
		</tr>	
		
		<tr>
			<td class="sh_1">
				Address :
			</td>
			<td>
				<form:input path="address.address" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				City :
			</td>
			<td>
				<form:input path="address.city" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Country :
			</td>
			<td>
				<form:input path="address.country" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Zipcode :
			</td>
			<td>
				<form:input path="address.postalCode" />
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Website :
			</td>
			<td>
				<form:input path="website" />
			</td>
		</tr>	
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
		--%><tr>
			<td colspan="4" class="rs">
		</tr>

		<tr>
			<td colspan="4" class="rs">
		</tr>

		<tr>
			<td colspan="4" class="rs">
		</tr>

		<tr colspan="2" align="right">
			<td>
				<input type="submit" name="addUser" value="Add User">
				<input type="submit" name="addUser" value="Cancel">
			</td>
		<tr>
	</table>
</form:form>
</body>

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
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<spring:bind path="user.*">
	<c:forEach items="${status.errorMessages}" var="error">
		<h2>
			Error code:
			<c:out value="${error}" />
		</h2>
	</c:forEach>
</spring:bind>

<form method="POST" action="editUser.htm">
	<table>
	<tr>
		<td colspan="4" class="rs"></td>
	</tr>
	<tr>
		<td colspan="4" class="rs"></td>
	</tr>
	<tr>
		<td colspan="4" class="rs"></td>
	</tr>
   </table>
	<spring:bind path="user.id">
		<input type="hidden" name="id"
			value="<c:out value="${status.value}"/>">
	</spring:bind>
	<table border="0" cellspacing="5" cellpadding="0">
<tr>
			<td class="sh_1">
				Phone Number :*
			</td>
			<td valign="middle" align="left">
				<spring:bind path="user.phoneNumber">
					<input type="text" maxlength="12" name="phoneNumber"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>	
		
		<tr>
			<td class="sh_1">
				Email :*
			</td>
			<td align="left">
				<spring:bind path="user.email">
					<input type="text" maxlength="50" name="email"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Password :*
			</td>
			<td valign="top" align="left">
				<spring:bind path="user.password">
					<input type="password" maxlength="15" name="password"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Confirm Password :*
			</td>
			<td valign="top" align="left">
				<spring:bind path="user.confirmPassword">
					<input type="password" maxlength="15" name="confirmPassword"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				First Name :*
			</td>
			<td  align="left">
				<spring:bind path="user.firstName">
					<input type="text" maxlength="15" name="firstName"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>

		<tr>
			<td class="sh_1">
				Last Name :*
			</td>
			<td valign="top" align="left">
				<spring:bind path="user.lastName">
					<input type="text" maxlength="15" name="lastName"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Address :
			</td>
			<td valign="middle" align="left">
				<spring:bind path="user.address.address">
					<input type="text" name="address.address"
						value="<c:out value="${status.value}"/>">
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="sh_1">
				City :
			</td>
			<td>
				<spring:bind path="user.address.city">
					<input type="text" name="address.city"
						value="<c:out value="${status.value}"/>">
				</spring:bind>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				State :
			</td>
			<td>
				<spring:bind path="user.address.province">
					<input type="text" maxlength="2" name="address.province"
						value="<c:out value="${status.value}"/>">
				</spring:bind>

			</td>
		</tr>
		<tr>
			<td class="sh_1">
				Zipcode :
			</td>
			<td>
				<spring:bind path="user.address.postalCode">
					<input type="text" maxlength="5" name="address.postalCode"
						value="<c:out value="${status.value}"/>">
				</spring:bind>

			</td>
		</tr>


		<tr>
			<td colspan="2" align="center">
				<input type="submit" class="button" name="Update" value="Update">
				<input type="submit" class="button" name="_cancel" value="Cancel">

			</td>
		</tr>
	</table>
</form>


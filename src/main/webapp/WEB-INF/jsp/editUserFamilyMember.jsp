
<%@ page contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/css-content.css" rel="stylesheet" type="text/css">
<html>
	<head>
		<title>Edit Family Member</title>
	</head>
	<body>
<spring:bind path="userFamilyMember.*">
	<c:forEach items="${status.errorMessages}" var="error">
		<h2>
			Error code:
			<c:out value="${error}" />
		</h2>
	</c:forEach>
</spring:bind>
		<form:form method="POST" action="editUserFamilyMember.htm"
			commandName="userFamilyMember">
			<spring:bind path="userFamilyMember.id">
				<input type="hidden" name="id"
					value="<c:out value="${status.value}"/>">
			</spring:bind>
			<br />
			<div class="sh">
				<tr align="center">
					<td>
						<h2>
							Edit Family Member
						</h2>
					</td>
				</tr>
			</div>
			<div>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
			</div>

			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td class="sh_1">
						First Name :*
					</td>
					<td>
						<spring:bind path="userFamilyMember.firstName">
							<input type="text" maxlength="15" name="firstName"
								value="<c:out value="${status.value}"/>">
						</spring:bind>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Last Name :*
					</td>
					<td>
						<spring:bind path="userFamilyMember.lastName">
							<input type="text" maxlength="15" name="lastName"
								value="<c:out value="${status.value}"/>">
						</spring:bind>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" class="button" name="Update" value="Update">
						<input type="submit" class="button" name="Cancel" value="Cancel">
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>

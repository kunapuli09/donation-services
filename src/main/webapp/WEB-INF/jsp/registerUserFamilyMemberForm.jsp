
<%@ page contentType="text/html"%>
<%@page import="com.starpath.domain.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--<link href="css/css-content.css" rel="stylesheet" type="text/css">-->
<html>
	<head>
		<title>Add Family Members</title>
	</head>
	<body>
		<form:form method="POST" action="registerUserMembers.htm" commandName="user">
			<input type="hidden" name="page" value="1" />
			<input type="hidden" value="<c:out value="${nextFamilyMember}"/>" />
			<br />

			<br />
			<div class="sh">
				<table height="25px;" width="100%">
			<tr colspan="4">
				<td >
					<strong>Family Member Information</strong>
				</td>
				
			</tr>
		</table>
			</div>
			<div>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
			</div>


			<table cellpadding="0" cellspacing="0" align="center" border="0">
				<tr>
					<td>
						First Name
					</td> 
					<td>
						Last Name
					</td>			
				</tr>
              
				<c:forEach  items="${user.userFamilyMembers}" var="userMember" >
					<tr>
						<td>
							<c:out value="${userMember.firstName}" />
						</td>
						<td>
							<c:out value="${userMember.lastName}" />
						</td>
					</tr>
			</c:forEach>
			</table>

			<table cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td colspan="4" class="rs"></td>
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
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td class="sh_1">
						First Name :*
					</td>
					<td>
						<form:input
							path="userFamilyMembers[${nextFamilyMember}].firstName" />
						<form:errors
							path="userFamilyMembers[${nextFamilyMember}].firstName"
							cssClass="error"></form:errors>
					</td>
				</tr>
				<tr>
					<td class="sh_1">
						Last Name :*
					</td>
					<td>
						<form:input path="userFamilyMembers[${nextFamilyMember}].lastName" />
						<form:errors
							path="userFamilyMembers[${nextFamilyMember}].lastName"
							cssClass="error"></form:errors>

					</td>
				</tr>
				<tr>
					<td colspan="4" class="rs"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="_target0" value="Back">
						&nbsp;
						<input type="submit" name="_target1" value="Add A Family Member">
						&nbsp;
						<input type="submit" name="_target2" value="Next">
						&nbsp;
						<input type="submit" name="_cancel" value="Cancel">
					</td>
				</tr>
			</table>
		</form:form>
	</body>
</html>

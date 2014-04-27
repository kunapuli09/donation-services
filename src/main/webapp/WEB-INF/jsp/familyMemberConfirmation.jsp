
<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link href="css/css-content.css" rel="stylesheet" type="text/css">
<html>
  <head>
    <title>User Registration Confirmation</title>
  </head>
  
  <body>
    <table height="25px;" width="100%">
			<tr colspan="4">
				<td >
					<strong>Confirmation</strong>
				</td>
			</tr>
		</table>
    <form:form commandName="user" method="POST" action="registerUserMembers.htm">
      <input type="hidden" name="page" value="2" />
      
      User: <c:out value="${user.firstName}" />&nbsp;<c:out value="${user.lastName}" /><br/>
      E-mail: <c:out value="${user.email}" /><br/>
       <c:out value="${message}" />
     
     <table cellpadding="0" cellspacing="0" align="center" border="1">
	 <tr>
	 <td>First Name</td>
	 <td>Last Name</td>
	 <td>Remove</td>
	 </tr>     	
	 <c:forEach items="${user.userFamilyMembers}" var="userMember" varStatus ="rowCounter">
		<c:if test="${userMember.firstName  != ''}">
		<tr>
			<td><c:out value="${userMember.firstName}" /></td>
			<td><c:out value="${userMember.lastName}" /></td>
			<input type="hidden" name="memberFirstName" value="<c:out value="${userMember.firstName}"/>">
			<td align="center">
			<input type="submit"  name="_target2" value="   X   "> 
			</td>
		</tr>
</c:if>      	
	 </c:forEach>    	
  </table>
      <p>Your information will be securely stored in temple database and is accessable to Temple Board Of Trustees Only. Please Click "Finish" to confirm your details.</p>
      <input type="submit" name="_target1" value="Back">&nbsp;<input type="submit" name="_finish" value="Finish">
    </form:form>
  </body>
</html>

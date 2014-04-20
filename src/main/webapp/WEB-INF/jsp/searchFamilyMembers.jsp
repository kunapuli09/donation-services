
<%@ page contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<link href="css/css-content.css" rel="stylesheet" type="text/css">
<div class="sh">
<tr><td colspan="4" class="rs"/></tr>
	<tr colspan="4">
		<td align="left"> 
		Search Family Members
		</td>
</tr>
</div>
<body>
<form method="post" action="searchMembersAction.htm">
<%
pageContext.setAttribute("action","FamilyMembers");
 %>
<div class="sh_1">
	<tr><td colspan="4" class="rs"/></tr>
	<authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
		<tr colspan="4">
			<td align="left">
				By User's Last Name :
			</td>
			<td>
				<input type="text" name="lastName" />	
			</td>
			<td>
				<input type="submit" name="Search" value="Search"/>			
			</td><br>
		</tr>
	</authz:authorize>
	<logic:present name="familyMembers">
	<display:table name="familyMembers" class="its" export="true" sort="list" 
		pagesize="10" requestURI="">
		<display:column property="firstName" title="First Name"
			sortable="true" headerClass="sortable" maxWords="1"/>
		<display:column property="lastName" title="Last Name" sortable="true"
			headerClass="sortable" maxWords="1" />
		<display:column property="id" headerClass="sortable" sortable="true" title="Member" media="html" decorator="com.starpath.mvc.UserFamilyMemberDecorator" />
		<display:column property="id" title="User" headerClass="sortable" sortable="true" media="html" decorator="com.starpath.mvc.UserDetailDecorator" />
		</display:table>
</logic:present>
</div>
</form>
</body>
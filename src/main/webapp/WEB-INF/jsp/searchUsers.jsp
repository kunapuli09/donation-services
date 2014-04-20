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
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<link href="css/main.css" rel="stylesheet" type="text/css">
<div class="sh">
<tr><td colspan="4" class="rs"/></tr>

	<tr colspan="4">
		<td align="left"> 
		Search Users
		</td>
</tr>
</div>
<body>
<form method="post" action="searchAction.htm">
	<div class="sh_1">
<tr><td colspan="4" class="rs"/></tr>

<authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
	<tr colspan="4">
		<td align="left">
			By Last Name :
		</td>
		<td>
			<input type="text" name="lastName" />	
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td align="right">
			By Phone :
		</td>
		<td>
			<input type="text" name="phoneNumber" />		
		</td>

		<td>
			<input type="submit" name="Search" value="Search"/>			
		</td>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		</td>

		<td>
			<a href="registerUser.htm"><b>Add User</b></a>	
		</td>

<br>
	</tr>
</authz:authorize>
	<logic:present name="users">
	<display:table name="users" class="its" export="true" sort="list"
		pagesize="10" requestURI="">
		<display:column property="firstName" title="First Name"
			sortable="true" headerClass="sortable" maxWords="1"/>
		<display:column property="lastName" title="Last Name" sortable="true"
			headerClass="sortable" maxWords="1" />
		<display:column property="email" title="Email" sortable="true"
			headerClass="sortable" />
		<display:column property="phoneNumber" title="Phone Number"
			sortable="true" headerClass="sortable" />
		<display:column property="address.city" title="City" sortable="true"
			headerClass="sortable"  />
		<display:column property="id" headerClass="sortable" sortable="true" title="User" media="html" decorator="com.starpath.mvc.UserDecorator" />
		<display:column property="id" title="Pledge" headerClass="sortable" sortable="true" media="html" decorator="com.starpath.mvc.PledgeDecorator" />
		<display:column property="id" title="Details" headerClass="sortable" sortable="true" media="html" decorator="com.starpath.mvc.UserDetailDecorator" />
		</display:table>
</logic:present>
</div>

</form>
</body>

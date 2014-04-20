<%--
    ----------------------------------------------------------------------
    Copyright ©2007-2008 by StarpathIT Inc., all rights reserved.
    ----------------------------------------------------------------------
     Log of Changes:
 
      Date            Author                  Description
      -------         ---------               -------------
      2008/02/02     (Swetha Kunapuli)(V) Initial Version
     ---------------------------------------------------------------------- 
 --%>
 <%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles"
	uri="http://jakarta.apache.org/struts/tags-tiles"%>
	<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>

<table width="100%"  cellpadding="0" cellspacing="0" border="0">
	<authz:authorize ifAllGranted="ROLE_MEMBER">
	<tr><a href="editUser.htm?email=<authz:authentication operation="username"/>"\>Update Profile</a></tr><br>
	</authz:authorize>
	 
	<authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
	<tr><a href="searchUsers.htm">Users</a></tr><br>
	<tr><a href="searchPledges.htm">Pledges</a></tr><br>
	<tr><a href="searchPaymentDetail.htm">PaymentDetails</a></tr><br>
	</authz:authorize>
	<authz:authorize ifAllGranted="ROLE_TRUSTEE">
	<tr><a href="templeRevenue.htm">Revenue</a></tr><br>	
	</authz:authorize>	
	<authz:authorize ifAnyGranted="ROLE_MEMBER, ROLE_VOLUNTEER, ROLE_TRUSTEE">
	<tr><a href="j_acegi_logout">Logoff</a></tr><br>
	</authz:authorize>	
</table>



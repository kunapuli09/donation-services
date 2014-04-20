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
<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<link href="css/main.css" rel="stylesheet" type="text/css">

<table align="center">
<tr class="sh_1" align="center">
<td><b>
<authz:authorize ifAnyGranted="ROLE_MEMBER, ROLE_VOLUNTEER, ROLE_TRUSTEE">
Hello <authz:authentication  operation="username"/>,<br>
</authz:authorize>
Welcome to Fund Raising Software provided by Starpath.
This software is intended  to keep track of the non-profit donations and 
 recognizing the available funds as the payments are made.
</b>

Please Click <a href="http://www.starpathit.com/Donation/">here</a> to register.<br>
</td>
</tr>
</table>

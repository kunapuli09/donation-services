<%--
    ----------------------------------------------------------------------
    Copyright �2007-2008 by StarpathIT Inc., all rights reserved.
    ----------------------------------------------------------------------
     Log of Changes:
 
      Date            Author                  Description
      -------         ---------               -------------
      2008/02/02     (Krishna M. Kunapuli)(V) Initial Version
     ---------------------------------------------------------------------- 
 --%>
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta name="Keywords" content="StarpathIT, Widgets" />
    <meta name="Description" content="Donation Management for non-profits" />
    <link href="css/css-print.css" rel="stylesheet" type="text/css" media="print" />
    <link href="css/css-content.css" rel="stylesheet" type="text/css" />
    <link href="css/displaytag.css" rel="stylesheet" type="text/css" media="all" />    
    <meta name="copyright" content="Copyright (c) 2006 StarpathIT, Inc. All rights reserved." />
    <title>Donation Management</title>
</head>
<body>    
    <div align="center">        
            <a href="http://www.starpathit.com/"><img src="images/templeBanner.jpg"></img></a>       
    
    <div id="nav-main">    
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
            
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td style="white-space: nowrap;">
                            <authz:authorize ifAllGranted="ROLE_MEMBER">
			       	<a href="editUser.htm?email=<authz:authentication operation="username"/>" style="border-style: none; font-size: 1em; font-family: Tahoma; color:#000000" \>Profile</a>
			     </authz:authorize>
   
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="width: 10px;">
                </td>
                <authz:authorize ifAnyGranted="ROLE_MEMBER, ROLE_VOLUNTEER, ROLE_TRUSTEE">
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td style="white-space: nowrap;">
                                <a href="searchUsers.htm?email=<authz:authentication operation="username"/>" style="border-style: none; font-family: Tahoma; font-size: 1em;color:#000000">Users</a>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="width: 10px;">
                </td>
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td style="white-space: nowrap;">
                                <a href="searchPledges.htm?email=<authz:authentication operation="username"/>" style="border-style: none; font-family: Tahoma; font-size: 1em;color:#000000"">Pledges</a>
                                </td>
                        </tr>
                    </table>
                </td>
                <td style="width: 10px;">
                </td>
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td style="white-space: nowrap;">
                                <a href="searchPaymentDetail.htm?email=<authz:authentication operation="username"/>" style="border-style: none; font-family: Tahoma; font-size: 1em;color:#000000"">Payments</a>
                                </td>
                        </tr>
                    </table>
                </td>                
                <td style="width: 10px;">
                </td>
                </authz:authorize>
                <authz:authorize ifAllGranted="ROLE_TRUSTEE">
                <td>
		                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
		                        <tr>
		                            <td style="white-space: nowrap;">
		                                <a href="templeRevenue.htm" style="border-style: none; font-family: Tahoma; font-size: 1em;color:#000000"">Revenue</a>
		                                </td>
		                        </tr>
		                    </table>
                </td>
                </authz:authorize>
                <td style="width: 10px;">
                </td>
                <authz:authorize ifAnyGranted="ROLE_MEMBER, ROLE_VOLUNTEER, ROLE_TRUSTEE">
		                <td>
				                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
				                        <tr>
				                            <td style="white-space: nowrap;">
				                                <a href="j_acegi_logout" style="border-style: none; font-family: Tahoma;font-size: 1em;color:#000000"">Logoff</a>
				                                </td>
				                        </tr>
				                    </table>
		                </td>
                </authz:authorize>
            </tr>
            
        </table>
        
    </div>
     
    <div id="content-container-three-column">
        
       <div>      
       <tiles:insert name="content" />
	</div>
	</div>
	 <div id="content-container-four-column" align="center" >
		<table cellpadding="0" cellspacing="0" border="0">
            <tr>            
                <td>
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td style="white-space: nowrap;">
   							 Copyright &copy; <%= new java.util.Date().getYear() + 1900 %> <a href="http://www.starpathit.com/">Starpath IT</a>
                            </td>
                        </tr>
                    </table>
                </td>
	 		</tr>
	</table>
      	<div>
        
    </div>
	
   
        
    
</body>
</html>
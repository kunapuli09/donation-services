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
<%@ taglib prefix="tiles" uri="http://struts.apache.org/tags-tiles" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta name="Keywords" content="StarpathIT, Widgets" />
    <meta name="Description" content="About StarpathIT, Inc., it's widgets, employees and company" />
    <meta name="copyright" content="Copyright (c) 2006 StarpathIT, Inc. All rights reserved." />
<!--    <link href="css/css-print.css" rel="stylesheet" type="text/css" media="print" />
    <link href="css/css-content.css" rel="stylesheet" type="text/css" /> -->
    <link href="css/displaytag.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/font-awesome.css" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.ico" />
    <title>StarpathIT</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <style type="text/css">
        body {
        padding-top: 60px;
        padding-bottom: 40px;
        }
        .sidebar-nav {
        padding: 9px 0;
        }
        .crap {
        padding: 9px 0;
        }
    </style>
    <style type="text/css">
        label.error { float: none; color: red; padding-left: .5em; vertical-align: top; }
        p { clear: both; }
        .submit { margin-left: 12em; }
    </style>
   
</head>
<body >
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="registerUser.htm">Donation Management</a>

            <authz:authorize ifNotGranted="ROLE_MEMBER">
		   <div id="nav-login" class="btn-group-horizontal pull-right">
                <a class="btn btn-success" href="registerUser.htm">SignUp</a>
                <a class="btn btn-info" href="login.htm"><i class="icon-user icon-white"></i>&nbsp;Login</a>
            </div>
           </authz:authorize>
             <authz:authorize ifAnyGranted="ROLE_MEMBER">
            <!--<div id="nav-logout" class="btn-group pull-right">
                <div class="btn-group">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-user"></i>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="editUser.htm?email=<authz:authentication operation="username"/>"><i class="icon-edit"></i>Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="j_acegi_logout"><i class="icon-eject"></i> Sign out</a></li>
                        
                    </ul>
                </div>

            </div>
        -->
        <div id="nav-logout" class="btn-group-horizontal pull-right">
                        <a class="btn btn-info" href="editUser.htm?email=<authz:authentication operation="username"/>"><i class="icon-edit"></i>Profile</a>
                        <a class="btn btn-danger" href="j_acegi_logout"><i class="icon-eject"></i> Sign out</a>
                

            </div>
            </authz:authorize>
            <authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="divider"></li>
                         <li><a href="searchUsers.htm"><i class="icon-plus-sign"></i>Users</a></i></li>
                        <li class="divider"></li>
                         <li><a href="searchPledges.htm"><i class="icon-plus-sign"></i>Pledges</a></i></li>
                        <li class="divider"></li>
                        <li class="divider"></li>
                         <li><a href="searchPaymentDetail.htm"><i class="icon-plus-sign"></i>Payments</a></i></li>
                        <li class="divider"></li>
                        <li><a href="j_acegi_logout"><i class="icon-eject"></i> Sign out</a></li>
                        <li class="divider"></li>
                    
                </ul>

            </div>
           </authz:authorize>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<div class="container">
    
    <tiles:insert name="content" />
    <hr>

    <footer>
       Copyright &copy; 2008 <a href="http://www.starpathit.com">StarpathIT, Inc.</a>
    </footer>

</div>   
</body>
</html>

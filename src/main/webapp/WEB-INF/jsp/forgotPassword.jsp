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
<body>
<form method="POST" action="forgotPassword.htm" >

<div>
		<tr>
			<td colspan="4" class="rs">Please Enter the following information and Click on Get Password button. Your Password will be sent to you by email at your registered email.</td>
		</tr>
	</div>


	<table cellpadding="0" cellspacing="0" align="left">
			<tr>
			<td colspan="4" class="rs">
		</tr>
		<tr>
			<td class="sh_1">
				Phone Number :
			</td>
			<td>
				<input type="text" name="phoneNumber" value=""/>
                Ex:xxx-xxx-xxxx
			</td>
		</tr>	
		<tr>
			<td class="sh_1">
				Email :*
			</td>
			<td>
              <input type="text" name="email" value=""/>
				
			</td>
		</tr>
		<tr>
			<td colspan="4" class="rs">
		</tr>

		<tr>
			<td colspan="4" class="rs">
		</tr>

		<tr>
			<td colspan="4" class="rs">
		</tr>
		<tr colspan="2" align="right">
			<td>
				<input type="submit" name="getPassword" value="Get Password">
				<input type="submit" name="_cancel" value="Cancel" />
			</td>
		<tr>
	</table>
</form>
</body>

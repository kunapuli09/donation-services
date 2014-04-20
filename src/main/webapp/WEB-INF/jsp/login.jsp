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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login</title>
</head>
<body>
<table border ="0" width="100%">
	<tr bgcolor="#980000">
		<td background="images/hashbar.png" style="padding-top: 5px;"
			valign="top"><strong>Donation</strong></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>		
		<td>Please Click on <a href="registerUser.htm">Sign
		Up</a> for a recurring termly pledge and if you are planning to pay by check.</td>		
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>		
		<td><form action="https://www.paypal.com/cgi-bin/webscr" method="post">
			<input type="hidden" name="cmd" value="_s-xclick">
			<input type="hidden" name="hosted_button_id" value="6622254">
			Please Click on <input type="image" src="https://www.paypal.com/en_US/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
			<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
			 to make quick donation by Paypal.
		</form>
	  </td>		
	</tr>
</table>
<br/>
<br/>
<table border="0" align="center" width="100%">
	<tr bgcolor="#980000">
		<td background="images/hashbar.png" style="padding-top: 5px;"
			valign="top"><strong>New User</strong></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>	
	<tr>
		
		<td>Please Click on <a
			href="registerUserMembers.htm">Register</a> to enter your details and
		also your family member details </td>
		
	</tr>
</table>
<br/>
<br/>
<table border="0" align="center" width="100%">	
	<tr bgcolor="#980000">
		<td background="images/hashbar.png" style="padding-top: 5px;"
			valign="top"><strong>Existing User</strong></td>
	</tr>
	<tr>
		<td><c:if test="${not empty param.login_error}">
			<font color="red">Login failed...try again</font>
			<br>
			<br>
		</c:if>
        </td>
	</tr>
</table>
<table>		
	<tr>
<form method="POST" action="<c:url value='j_acegi_security_check'/>">    
		<td class="sh_1">Email:&nbsp;&nbsp;&nbsp;</td>
		<td><input type="text" name="j_username"></td>
	</tr>
	<tr>
		<td class="sh_1">Password:</td>
		<td><input type="password" name="j_password"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Login"></td>
	</tr>
	</form>
    Please Click on <a href="forgotPasswordForm.htm">Forgot Password</a>
	</td>	
</table>
</body>
</html>


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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<link href="css/main.css" rel="stylesheet" type="text/css">
<html>
  <head>
    <title>User Confirmation</title>
  </head>
  
  <body>
    <tr colspan="3">				
				<td >
					Confirmation
				</td>
	</tr>
    <form:form commandName="paymentDetail" method="POST" action="addPledge.htm">
      <input type="hidden" name="page" value="1" />
      <c:out value="${message}" />
      <p>You will be contacted by a volunteer in the next 24 hours before processing your cheque information</p>   

      <p>Click "Finish" to confirm your pledge.</p>
      <input type="submit" name="_target0" value="Back">&nbsp;<input type="submit" name="_finish" value="Finish">
    </form:form>
  </body>
</html>

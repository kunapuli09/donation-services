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
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="rr" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<link href="css/main.css" rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <head>
 <script type="text/javascript"> 
 </script>
 </head>
    <spring:bind path="pledge.*">
        <c:forEach items="${status.errorMessages}" var="error">
<h2>        Error code: <c:out value="${error}"/></h2>
        </c:forEach>
    </spring:bind>
  
    <form method="POST" action="editPledge.htm">
      <spring:bind path="pledge.id">  
        <input type="hidden" name="id" value="<c:out value="${status.value}"/>">
      </spring:bind>
    <table border="0" cellspacing="5" cellpadding="0">
      <tr><td valign="top" align="right" class="sh_1">
       Pledge Amount :* 
      </td><td valign="top" align="left">
      <spring:bind path="pledge.pledgeAmount">  
        <input type="text" maxlength="15" name="pledgeAmount" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right" class="sh_1">
        Number of Payment Terms :
      </td><td valign="middle" align="left"> 
        <rr:frequencyTerms path="pledge.paymentFrequency" />
      </td></tr> 
      <tr><td valign="middle" align="right" class="sh_1">
        Comment :
      </td><td valign="middle" align="left">      
      <spring:bind path="pledge.comment">      	
        <input type="text" maxlength="15" name="comment" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>      
      
      <tr><td colspan="2" align="center">            
      <input type="submit" class="button" name="Update"
					value="Update">
		<input type="submit" class="button" name="Cancel"
					value="Cancel">
	
      </td></tr>
    </table>
    </form>
  
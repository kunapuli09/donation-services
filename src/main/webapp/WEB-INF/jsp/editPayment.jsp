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
 var cal = new CalendarPopup();
 </script>
 </head>
    <spring:bind path="paymentDetail.*">
        <c:forEach items="${status.errorMessages}" var="error">
<h2>        Error code: <c:out value="${error}"/></h2>
        </c:forEach>
    </spring:bind>
  
    <form method="POST" action="editPayment.htm">
      <spring:bind path="paymentDetail.id">  
        <input type="hidden" name="id" value="<c:out value="${status.value}"/>">
      </spring:bind>
    <table border="0" cellspacing="5" cellpadding="0">
      <tr><td valign="middle" align="right" class="sh_1">
       Payment Amount :
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.paymentAmount">  
        <input type="text" maxlength="15" name="paymentAmount" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      <tr><td valign="middle" align="right" class="sh_1">
       Payment Date :
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.paymentDate">  
        <input type="text" maxlength="11" name="paymentDate" value="<c:out value="${status.value}"/>"> &nbsp<img src="images/cal.gif"
					onclick="cal.select(document.forms[0].paymentDate,'img1','MM/dd/yyyy'); return false;"
					name="img1" id="img1" />
      </spring:bind>
      </td></tr>   
      <tr><td valign="top" align="right" class="sh_1">
       Payment Received :* 
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.paymentReceived">  
        <input type="hidden" name="_<c:out value="${status.expression}"/>" value="visible" />
		<input type="checkbox" name="<c:out value="${status.expression}" />" value="true" <c:if test="${status.value}">checked</c:if> /> 
      </spring:bind>
      </td></tr>
      
      <tr><td valign="middle" align="right" class="sh_1">
       Bank Name :
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.bankname">  
        <input type="text" maxlength="15" name="bankname" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      <tr><td valign="middle" align="right" class="sh_1">
       Bank Account :
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.account">  
        <input type="text" maxlength="15" name="account" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      <tr><td valign="middle" align="right" class="sh_1">
       Bank Swift :
      </td><td valign="top" align="left">
      <spring:bind path="paymentDetail.swift">  
        <input type="text" maxlength="15" name="swift" value="<c:out value="${status.value}"/>">
      </spring:bind>
      </td></tr>
      
      <tr><td colspan="2" align="center">            
      <input type="submit" class="button" name="Update"
					value="Update">
		<input type="submit" class="button" name="_cancel"
					value="Cancel">
	
      </td></tr>
    </table>
    </form>
  
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
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
 var cal = new CalendarPopup();
 var testresults;
 function checkFields()
{
	var str=document.all.startDate.value;
	var str1=document.all.endDate.value;
	if ((str!=' ') || (str!=null))
	{
		testresults=true
	}
	else
	{
		alert("Start and End Dates are Required to Report");
		document.all.startDate.value = "";
		document.all.endDate.value = "";
		testresults=false
	}
	return (testresults)
}
 </script>

<h3>
	Get User, Pledge and Revenue Details
</h3>
<br>
***Start and End Dates are Mandatory to see a report.
<form method="POST" onsubmit = "return checkFields()" action="templeRevenue.htm">
	<tr>
		<td valign="middle" align="right" class="sh_1">
			Starting
		</td>
		<td valign="top" align="left">
			<input type="text" maxlength="11" name="startDate"
				value="<c:out value="${status.value}"/>">
			&nbsp
			<img src="images/cal.gif"
				onclick="cal.select(document.forms[0].startDate,'img1','MM/dd/yyyy'); return false;"
				name="img1" id="img1" />
		</td>
	</tr>
	<tr>
		<td valign="middle" align="right" class="sh_1">
			Ending
		</td>
		<td valign="top" align="left">
			<input type="text" maxlength="11" name="endDate"
				value="<c:out value="${status.value}"/>">
			&nbsp
			<img src="images/cal.gif"
				onclick="cal.select(document.forms[0].endDate,'img1','MM/dd/yyyy'); return false;"
				name="img1" id="img1" />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" class="button" name="report" value="Show Report">

<table border="0" cellspacing="5" cellpadding="0">
	<% com.starpath.domain.Report report = (com.starpath.domain.Report)request.getAttribute("report");   %>
	<tr>
		<td class="sh_1">
			Total Revenue As Of Today :
		</td>
		<td align="left">
			<c:out value="${report.totalRevenueAsOfDate}" />
		</td>
	</tr>
	<%if(report.getRevenueThisPeriod() >  0.0d ){ %>
	<tr>
		<td class="sh_1">
			Revenue This Period :
		</td>
		<td align="left">
			<c:out value="${report.revenueThisPeriod}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getTotalNumberOfUsers() > 0 ){ %>
	<tr>
		<td class="sh_1">
			Total Number Of Members :
		</td>
		<td align="left">
			<c:out value="${report.totalNumberOfUsers}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getNumberOfNewPledges() > 0 ){ %>
	<tr>
		<td class="sh_1">
			Number Of New Pledges :
		</td>
		<td align="left">
			<c:out value="${report.numberOfNewPledges}" />
		</td>
	</tr>	
	<%} %>
	<%if(report.getTotalActivePledges() > 0 ){ %>
	<tr>
		<td class="sh_1">
			Number Of Active Pledges :
		</td>
		<td align="left">
			<c:out value="${report.totalActivePledges}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getTotalDisabledPledges() > 0 ){ %>
	<tr>
		<td class="sh_1">
			Number Of Disabled Pledges :
		</td>
		<td align="left">
			<c:out value="${report.totalDisabledPledges}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getNumberOfPaymentsReceived() > 0 ){ %>
	<tr>
		<td class="sh_1">
			Number Of Payments Received:
		</td>
		<td align="left">
			<c:out value="${report.numberOfPaymentsReceived}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getUsersDonated25000Up() != null && ! ("".equals(report.getUsersDonated25000Up())) ){ %>
	<tr>
		<td class="sh_1">
			25000 and Up Contributors:
		</td>
		<td align="left">
			<c:out value="${report.usersDonated25000Up}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getUsersDonated10000Up() != null && ! ("".equals(report.getUsersDonated10000Up())) ){ %>
	
	<tr>
		<td class="sh_1">
			10000 and Up Contributors:
		</td>
		<td align="left">
			<c:out value="${report.usersDonated10000Up}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getUsersDonated5000Up() != null && ! ("".equals(report.getUsersDonated5000Up())) ){ %>
	<tr>
		<td class="sh_1">
			5000 and Up Contributors:
		</td>
		<td align="left">
			<c:out value="${report.usersDonated5000Up}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getUsersDonated2500Up() != null && ! ("".equals(report.getUsersDonated2500Up())) ){ %>
	
	<tr>
		<td class="sh_1">
			2500 and Up Contributors:
		</td>
		<td align="left">
			<c:out value="${report.usersDonated2500Up}" />
		</td>
	</tr>
	<%} %>
	<%if(report.getUsersDonated100Up() != null && ! ("".equals(report.getUsersDonated100Up())) ){ %>
	<tr>
		<td class="sh_1">
			100 and Up Contributors:
		</td>
		<td align="left">
			<c:out value="${report.usersDonated100Up}" />
		</td>
	</tr>
	<%} %>
</table>
</form>
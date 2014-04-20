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
<div class="sh">
	<tr>
		<td colspan="4" class="rs" />
	</tr>

	<tr colspan="4">
		<td align="left">
			Search Pledges
		</td>
	</tr>
</div>
<form method="post" action="searchPledgeAction.htm">
	<div class="sh_1">
		<tr>
			<td colspan="4" class="rs" />
		</tr>
<authz:authorize ifAnyGranted="ROLE_VOLUNTEER, ROLE_TRUSTEE">
		<tr colspan="4">
			<td align="left">
				Search Pledge by User's Last Name :
			</td>
			<td>
				<input type="text" name="lastName" />
			</td>
			<td>
				<input type="submit" name="Search" value="Search" />
			</td>

		</tr>
</authz:authorize>
		<logic:present name="pledges">
			<display:table name="pledges" class="its" export="false" sort="list"
				pagesize="10" requestURI="">
				<display:column property="user.lastName" sortable="true"
					title="Last Name" maxWords="1" headerClass="sortable" />
				<display:column property="pledgeType" sortable="true"
					title="Pledge Type" headerClass="sortable" />
				<display:column property="paymentStatus" sortable="true"
					maxWords="1" title="Status" headerClass="sortable" />
				<%--<display:column property="startDate" format="{0,date,dd-MMM-yyyy}"
					sortable="true" title="Start Date" headerClass="sortable" />
				--%><display:column property="pledgeAmount" sortable="true"
					title="Pledge Amount" headerClass="sortable" />
				<display:column property="paymentFrequency" sortable="true"
					title="Pmt Freq" headerClass="sortable" />
				<display:column property="comment" sortable="true" title="Comment"
					maxWords="1" headerClass="sortable" />
				<display:column property="id" title="Pledge" headerClass="sortable"
					sortable="true" decorator="com.starpath.mvc.PledgeDecorator" />
				<authz:authorize ifAllGranted="ROLE_TRUSTEE">
					<display:column property="id" title="Tax Email" sortable="true"
						headerClass="sortable" decorator="com.starpath.mvc.UserDecorator" />
				</authz:authorize>
			</display:table>
		</logic:present>
	</div>
</form>
</body>
<%@ attribute name="path" required="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:select path="${path}" onchange="onPldgType(this)" >
  <form:option value="--" label="--CHOOSE--"/>
  <form:option value="STANDARD" label="STANDARD CONTRIBUTION"/>
  <form:option value="TERM" label="TERM PLEDGE"/>   
</form:select>


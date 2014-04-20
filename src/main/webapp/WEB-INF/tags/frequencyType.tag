<%@ attribute name="path" required="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:select path="${path}" id="freqType">
  <form:option value="--" label="--CHOOSE--"/>
  <form:option value="MONTHLY" label="MONTHLY"/>
</form:select>
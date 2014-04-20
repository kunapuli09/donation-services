<%@ attribute name="path" required="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:select path="${path}">
  <form:option value="--" label="--CHOOSE--"/>
  <form:option value="ROLE_MEMBER" label="MEMBER"/>
  <form:option value="ROLE_VOLUNTEER" label="VOLUNTEER"/> 
  <form:option value="ROLE_TRUSTEE" label="TRUSTEE"/>
</form:select>


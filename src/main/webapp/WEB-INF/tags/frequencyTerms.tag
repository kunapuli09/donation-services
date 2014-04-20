<%@ attribute name="path" required="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:select path="${path}" id="freqTerm">
  <form:option value="--" label="--CHOOSE--"/>
  <form:option value="0" label="ONE TIME"/>
  <form:option value="1" label="ONE - MONTHLY"/>
  <form:option value="2" label="TWO - MONTHLY"/>
  <form:option value="3" label="THREE - MONTHLY"/>  
  <form:option value="4" label="FOUR - MONTHLY"/>
  <form:option value="5" label="FIVE - MONTHLY"/>
  <form:option value="6" label="SIX - MONTHLY"/>  
  <form:option value="7" label="SEVEN - MONTHLY"/>
  <form:option value="8" label="EIGHT - MONTHLY"/>
  <form:option value="9" label="NINE - MONTHLY"/>  
  <form:option value="10" label="TEN - MONTHLY"/>
  <form:option value="11" label="ELEVEN - MONTHLY"/>
  <form:option value="12" label="TWELE - MONTHLY"/>  
  <form:option value="24" label="TWO YEARS"/>
  <form:option value="36" label="THREE YEARS"/>
  <form:option value="48" label="FOUR YEARS"/>
  <form:option value="60" label="FIVE YEARS"/>
</form:select>

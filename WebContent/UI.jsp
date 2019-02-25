<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="StyleSheet" href="res/mc.css" type="text/css" title="cse4413" media="screen, print"/>
<script type="text/javascript" src="res/mc.js">;</script>
<title>OSAP Calculator</title>
</head>
<body>
<form action="" method ="post" onsubmit="return validate();">
<FIELDSET>
  <legend>${initParam['legendName']}</legend>
  <table>
  	<c:if test="${not empty requestScope['invalidEntry']}">
  	<tr>
  	<td><div class="error">Invalid Input!</div></td>
  	</tr>
  	</c:if>
    <tr>
      <td><label for="principal">Principal:<br/>(Present loan amount)</label>
          <div class="jsError" id="principalErr">Principal must not be negative!</div>
      </td>            
      <c:choose>
        <c:when test="${not empty principal}">
          <td><input type="number" step="0.01" name="principal" id="principal" value="${principal}"/></td>
        </c:when>
        <c:otherwise>
          <td><input type="number" step="0.01" name="principal" id="principal" value="1000"/></td>
        </c:otherwise>
      </c:choose>
  </tr>
  <tr>
    <td><label for="interest">Annual Interest Rate:</label>
    	<div class="jsError" id="interestErr">Interest must be between 0 and 100!</div>	
    </td>
    <c:choose>
      <c:when test="${not empty interest}">
        <td><input type="number" step="0.01" name="interest" id="interest" value="${interest - initParam['fixedInterest']}"/></td>
      </c:when>
      <c:otherwise>
        <td><input type="number" step="0.01" name="interest" id="interest" value="6"/></td>
      </c:otherwise>
    </c:choose>
  </tr>
  <tr>
    <td><label for="period">Payment Period:<br/>(Total number of months)</label>
    	<div class="jsError" id="periodErr">Period must not be negative!</div>
    </td>
    <c:choose>
      <c:when test="${not empty period}">
        <td><input type="number" step="0.01" name="period" id="period" value="${period}"/></td>
      </c:when>
      <c:otherwise>
        <td><input type="number" step="0.01" name="period" id="period" value="48"/></td>
      </c:otherwise>
    </c:choose>
  </tr>
  <tr>
    <td><label for="grace" class="radio">Grace Period:<br/>(Take advantage of the 6 month grace period and include grace period interest with your loan balance)</label></td>
    <c:choose>
      <c:when test="${grace == 'true'}">
        <td><input type="checkbox" name="grace" id="grace" checked="checked"/></td>
      </c:when>
      <c:otherwise>
        <td><input type="checkbox" name="grace" id="grace"/></td>
      </c:otherwise>
    </c:choose>
  </tr>  	
    <c:choose>
      <c:when test="${advanced == 'true'}">
      	<tr>
      	<td><label for="studentNum">Student Number:<br/>(Your University Provided Student Number)</label>
      		<div class="jsError" id="studentErr">Student Number must be valid!</div>
    	</td>
    	<c:choose>
    	  <c:when test="${not empty studentNum}">
            <td><input type="number" name="studentNum" id="studentNum" step="1" value="${studentNum}"/></td>
          </c:when>
          <c:otherwise>
      	    <td><input type="number" name="studentNum" id="studentNum" step="1" value="${initParam['studentNum']}"/></td>
          </c:otherwise>
        </c:choose>
        </tr>
        <tr>
      	<td><label for="rememberNum">Remember Student Number:<br/></label>
    	</td>
    	<c:choose>
    	  <c:when test="${not empty rememberNum}">
    	    <td><input type="checkbox" name="rememberNum" id="rememberNum" checked="checked"/></td>
          </c:when>
          <c:otherwise>
            <td><input type="checkbox" name="rememberNum" id="rememberNum"/></td>
          </c:otherwise>
        </c:choose>
        </tr>
      </c:when>
    </c:choose>
  <tr>
    <td><input type="submit" name="calculate" value="Submit"/></td>
    <td><button class="ajaxCalc" name="ajaxCalc" value="ajaxSubmit" onclick="if (validate()) {doSimpleAjax('/ismail_213235403_test1/Startup/Ajax/');return false;}">Submit(AJAX)</button></td>
  </tr>
  </table>
  <div class="ajaxTarget" id="ajaxTarget"></div>
  </FIELDSET>
</form> 
</body>
</html>
</jsp:root>
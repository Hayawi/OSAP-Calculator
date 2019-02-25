<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"	
	xmlns:f="http://java.sun.com/jsp/jstl/fmt" >
	<jsp:directive.page contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1" session="true"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="StyleSheet" href="res/mc.css" type="text/css" title="cse4413" media="screen, print"/>
<title>OSAP Calculator</title>
</head>
<body>
	<form action="" method ="get">
		<FIELDSET>
			<legend>${initParam['legendName']}</legend>
			<c:choose>
			  <c:when test="${not empty showPeriod}">
			  	<p>The Maximum Period Value that has been entered is: ${maxPeriod} Months</p>
			  </c:when>
			  <c:otherwise>
			    <p>The Maximum Principal Value that has been entered is: <f:formatNumber type="currency">${maxPrincipal}</f:formatNumber></p>
			  </c:otherwise>
			</c:choose>
		</FIELDSET>
	</form>
</body>
</html>
</jsp:root>
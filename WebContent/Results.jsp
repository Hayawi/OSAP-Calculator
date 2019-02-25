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
		<legend>${initParam['legendName']} ${param.studentNum}</legend>
		<p>Based on<br/>Principal: <f:formatNumber type="currency">${principal}</f:formatNumber><br/>
		Interest Rate: ${interest}%<br/>Period: ${period} Months
			<c:if test="${grace == 'true'}">
				<br/>Grace Period: ${gracePeriod} Months<br/>
			</c:if>
		</p>
		<p>Your Monthly Payment is: <f:formatNumber type="currency">${payments}</f:formatNumber><br/>
			<c:if test="${grace == 'true'}">
				Your Grace Period Interest is: <f:formatNumber type="currency">${graceInterest}</f:formatNumber>
			</c:if>
		</p>
		<input type="submit" value="Recompute"/>
		</FIELDSET>
	</form>
</body>
</html>
</jsp:root>
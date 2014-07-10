<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Main Menu</title>
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
	</head>
	<body>
		<h1>Family History</h1>
		
		<h3><a href="addcensushousehold">Add a census household</a></h3>
		<h3><a href="addmarriage">Add a marriage</a></h3>
		<h3><a href="addperson">Add a person</a></h3>
		<h3><a href="censushouseholdindex">Census household index</a></h3>
		<h3><a href="personindex">Person index</a></h3>
		<h3><a href="sourceindex">Source index</a></h3>
	</body>
</html>
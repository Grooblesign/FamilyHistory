<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Source Index</title>	
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
	</head>
	<body>
		<a href="index">Main Menu</a>
		
		<h1>Source Index</h1>

		<table class="sourceTable">
			<thead>
	    		<tr>
	      			<th>Title</th>
	      			<th>Publisher</th>
	      			<th>Author</th>
	      			<th>Date</th>
	    		</tr>
	  		</thead>		
	  		<tbody>		
				<c:forEach items="${sources}" var="source">
				<tr>
					<td>${source.title}</td>
					<td>${person.publisher}</td>
					<td>${person.author}</td>
					<td>${person.date}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>				
	</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Census Household Index</title>
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
	</head>
	<body>
		<a href="index">Main Menu</a>
	
		<h1>Census Household Index</h1>

		<c:forEach items="${censuses}" var="census">
			<h2>${census.title}</h2>
			<table width="75%">
			<thead>
	    		<tr>
	      			<th>Address</th>
	      			<th>Head</th>
	      			<th>Reference</th>
	    		</tr>
	  		</thead>		
	  		<tbody>		
			<c:forEach items="${censusHouseholds}" var="censusHousehold">
				<c:if test="${censusHousehold.censusId == census.id}">
				<tr>
					<td width="50%">
						<a href="census/${censusHousehold.censusId}/censushousehold/${censusHousehold.id}">${censusHousehold.address}</a>
					</td>
					<td>
						${censusHousehold.head}
					</td>
					<td>
						${censusHousehold.reference}
					</td>
				</tr>
				</c:if>
			</c:forEach>
	  		</tbody>		
			</table>
		</c:forEach>
	</body>
</html>
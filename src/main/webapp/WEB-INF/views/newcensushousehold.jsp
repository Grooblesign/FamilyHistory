<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add a census household</title>
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">

			function save() {
				// alert($('#censusSelect').find(":selected").val());
				// alert($('#addressInput').val());
				
				var censusId = $('#censusSelect').find(":selected").val();
				
	 			var data = 
	 				'{ ' + 
	 				'"address": "' + $("#addressInput").val() + '",' +
	 				'"censusId": "' + censusId + '",' +
	 				'"folio": "' + $("#folioInput").val() + '",' +
	 				'"page": "' + $("#pageInput").val() + '",' +
	 				'"piece": "' + $("#pieceInput").val() + '"' +
					'}';
				
				// alert(data);
				
	 			$.ajax({
	 	        	url : 'census/' + censusId + '/censushousehold',
	 	        	type: 'PUT',
	 	        	data: data,
	 	        	dataType: 'json',
	 	        	success : function(result) {
	 	        		window.location.replace('census/' + censusId + '/censushousehold/' + result.result);
	 	            },
	 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                	alert("Status: " + textStatus); 
	                	alert("Error: " + errorThrown);
	 				}
	 	        });
			}

		</script>
	</head>
	<body>
		<a href="index">Main Menu</a>
	
		<h1>Add a census household</h1>

		<table>
			<tr>
				<td class='header'>Census</td>
      			<td>
      				<select id="censusSelect">
						<c:forEach items="${censuses}" var="census">
							<option value="${census.id}">${census.title}</option>
						</c:forEach>
					</select>
      			</td>
			</tr>
			<tr>
				<td class='header'>Address</td>
      			<td>
      				<input type="text" id="addressInput" size="80em" />
      			</td>
			</tr>
			<tr>
				<td class='header'>Piece</td>
      			<td>
      				<input type="text" id="pieceInput" size="30em" />
      			</td>
			</tr>
			<tr>
				<td class='header'>Folio</td>
      			<td>
      				<input type="text" id="folioInput" size="30em" />
      			</td>
			</tr>
			<tr>
				<td class='header'>Page</td>
      			<td>
      				<input type="text" id="pageInput" size="30em" />
      			</td>
			</tr>
		</table>
		<br />
		<input type="button" id="saveButton" value="Save" onclick="save();" />
	</body>
</html>
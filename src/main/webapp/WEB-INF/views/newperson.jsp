<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add a person</title>
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			function savePerson() {
	 			var data = 
	 				'{ ' + 
	 				'"forenames": "' + $("#forenamesInput").val() + '",' +
	 				'"gender": "' + $("#genderInput").val() + '",' +
	 				'"surname": "' + $("#surnameInput").val() + '"' +
					'}';
					
				alert(data);
				
	 			$.ajax({
	 	        	url : 'person',
	 	        	type: 'PUT',
	 	        	data: data,
	 	        	success : function(result) {
	 	        		alert(result);
	 	        		window.location.replace('person/' + result);	 
	 	        	}
	 	        });
			}
		</script>
	</head>
	<body>
		<a href="index">Main Menu</a>
	
		<h1>Add a person</h1>
	
		<table width="60%">
			<tr>
      			<td width="20%" class="header">Forenames</td>
      			<td>
      				<input type="text" id="forenamesInput" width="30em" />
      			</td>
     		</tr>
			<tr>
      			<td width="20%" class="header">Surname</td>
      			<td>
      				<input type="text" id="surnameInput" width="30em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Gender</td>
      			<td>
      				<input type="text" id="genderInput" width="30em" />
      			</td>
     		</tr>
		</table>
		<br />
		<input type="button" id="savePersonButton" value="Save" onclick="savePerson();"/>
	</body>
</html>
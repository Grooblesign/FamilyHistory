<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Person Index</title>	
		<link rel="stylesheet" href="/FamilyHistory/resources/stylesheet.css" type="text/css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
		<script>
			function load() {
				var persons = new Array(); 
					
	 			$.ajax({
	 	        	url : '/FamilyHistory/person',
	 	        	async: false,
	 	        	dataType: 'json',
	 	        	type: 'GET',
	 	        	success : function(result) {
	 	        		persons = result;
	 	            },
	 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                	alert("Status: " + textStatus + " Error: " + errorThrown);
	 				}
	 	        });
				
	 			persons.forEach(
	 				function(person) {
						var className = person.surname == "" ? "unknown" : person.surname.substring(0, 1);
					
						var html = "";

						html = html + "<tr class='%1' id='%2' onclick='selectPerson(%2);'>".replace("%1", className).replace(/%2/g, person.id);
						html = html + "<td>%1</td>".replace("%1", person.surname);
						html = html + "<td>%1</td>".replace("%1", person.forenames);
						html = html + "<td>%1</td>".replace("%1", person.birthDate);
						html = html + "<td>%1</td>".replace("%1", person.birthPlace);
						html = html + "</tr>";
					
						$('#personTable tbody').append(html);
	 				}
	 			);
	 			
				var rows = $('table#personTable tr');
				rows.hide();
				
				rows.filter('.A').show();
				
				rows = $('table#personTable thead tr');
				rows.show();
			}
			
			function selectLetter(cell) {
				$('.selectedLetterCell').addClass('letterCell').removeClass('selectedLetterCell');		
				
				cell.className = 'selectedLetterCell';
				
				var letter = cell.innerHTML;
				if (letter == '?') {
					letter = 'unknown';
				}
				
				var rows = $('table#personTable tr');
				rows.hide();
				rows.filter('.' + letter).show();
		
				rows = $('table#personTable thead tr');
				rows.show();
			}
			
			function selectPerson(personId) {
				var row = $('table#personTable tr#' + personId);
				
				// alert(row);
				
				window.location.href = '/FamilyHistory/person/' + personId;
			}
		</script>
	</head>
	<body onload="load();">
		<a href="index">Main Menu</a>
		
		<h1>Person Index</h1>
		
		<table>
			<tr>
				<td class="selectedLetterCell" onclick="selectLetter(this);">A</td>
				<td class="letterCell" onclick="selectLetter(this);">B</td>
				<td class="letterCell" onclick="selectLetter(this);">C</td>
				<td class="letterCell" onclick="selectLetter(this);">D</td>
				<td class="letterCell" onclick="selectLetter(this);">E</td>
				<td class="letterCell" onclick="selectLetter(this);">F</td>
				<td class="letterCell" onclick="selectLetter(this);">G</td>
				<td class="letterCell" onclick="selectLetter(this);">H</td>
				<td class="letterCell" onclick="selectLetter(this);">I</td>
				<td class="letterCell" onclick="selectLetter(this);">J</td>
				<td class="letterCell" onclick="selectLetter(this);">K</td>
				<td class="letterCell" onclick="selectLetter(this);">L</td>
				<td class="letterCell" onclick="selectLetter(this);">M</td>
				<td class="letterCell" onclick="selectLetter(this);">N</td>
				<td class="letterCell" onclick="selectLetter(this);">O</td>
				<td class="letterCell" onclick="selectLetter(this);">P</td>
				<td class="letterCell" onclick="selectLetter(this);">Q</td>
				<td class="letterCell" onclick="selectLetter(this);">R</td>
				<td class="letterCell" onclick="selectLetter(this);">S</td>
				<td class="letterCell" onclick="selectLetter(this);">T</td>
				<td class="letterCell" onclick="selectLetter(this);">U</td>
				<td class="letterCell" onclick="selectLetter(this);">V</td>
				<td class="letterCell" onclick="selectLetter(this);">W</td>
				<td class="letterCell" onclick="selectLetter(this);">X</td>
				<td class="letterCell" onclick="selectLetter(this);">Y</td>
				<td class="letterCell" onclick="selectLetter(this);">Z</td>
				<td class="letterCell" onclick="selectLetter(this);">?</td>
			</tr>
		</table>
		<br />
		<table id="personTable">
			<thead>
	    		<tr>
	      			<th>Surname</th>
	      			<th>Forenames</th>
	      			<th>Birth date</th>
	      			<th>Birth place</th>
	    		</tr>
	  		</thead>	
	  		<tbody>
	  		</tbody>	
		</table>
	</body>
</html>
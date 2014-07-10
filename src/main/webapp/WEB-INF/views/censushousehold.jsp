<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Census Household</title>
	<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
 		$(document).ready(function(){
        	$("#personForm").hide();
    	});
 		
 		function addPerson(censusId, censusHouseholdId) {
 			$('#householdTable').hide();
 			
			 $("#idInput").val("");
			 $("#nameInput").val("");
 			 $("#relationshipToHeadInput").val("");
 			 $("#ageInput").val("");
 			 $("#statusInput").val("");
 			 $("#occupationInput").val("");
 			 $("#birthplaceInput").val("");

 			 $("#saveEditPersonButton").unbind('click').click(function() { CreatePerson(censusId, censusHouseholdId); });

 			 $('#personForm').show(); 			
 		}
 		
 		function editPerson(censusId, censusHouseholdId, censusHouseholdPersonId, personId) {
 			 $('#householdTable').hide();
 			 
 			 $("#idInput").val(personId);
 			 $("#nameInput").val($("#" + censusHouseholdPersonId + "_nameCell").text());
 			 $("#relationshipToHeadInput").val($("#" + censusHouseholdPersonId + "_relationshipToHeadCell").text());
 			 $("#ageInput").val($("#" + censusHouseholdPersonId + "_ageCell").text());
 			 $("#statusInput").val($("#" + censusHouseholdPersonId + "_statusCell").text());
 			 $("#occupationInput").val($("#" + censusHouseholdPersonId + "_occupationCell").text());
 			 $("#birthplaceInput").val($("#" + censusHouseholdPersonId + "_birthplaceCell").text());
 			 
 			 $("#saveEditPersonButton").unbind('click').click(function() { UpdatePerson(censusId, censusHouseholdId, censusHouseholdPersonId); });
 			 
 			 $('#personForm').show(); 			
 		}
 		
 		function CreatePerson(censusId, censusHouseholdId) {
 			var personId = 0;
 			if ($("#idInput").val() != "") {
 				personId = $("#idInput").val();
 			}
 			
 			var data = 
 				'{ ' + 
 				'"personId": "' + personId + '",' +
 				'"name": "' + $("#nameInput").val() + '",' +
 				'"relationshipToHead": "' + $("#relationshipToHeadInput").val() + '",' +
 				'"age": "' + $("#ageInput").val() + '",' +
 				'"status": "' + $("#statusInput").val() + '",' +
 				'"occupation": "' + $("#occupationInput").val() + '",' +
 				'"birthplace": "' + $("#birthplaceInput").val() + '"' +
				'}';
				
 			$.ajax({
 	        	url : '../../../census/' + censusId + '/censushousehold/' + censusHouseholdId + '/person',
 	        	type: 'PUT',
 	        	async: false,
 	        	dataType: 'json',
 	        	data: data,
 	        	success : function(result) {
 	        		// alert(result.result);
 	            },
 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
                	alert("Status: " + textStatus); 
                	alert("Error: " + errorThrown);
 				}
 	        });
 			
 			// $('#personForm').hide();
 			// $('#householdTable').show();
 			
             location.reload();
 		}
 		
 		function DeletePerson(censusId, censusHouseholdId, censusHouseholdPersonId) {
 			$.ajax({
 	        	url : '../../../census/' + censusId + '/censushousehold/' + censusHouseholdId + '/person/' + censusHouseholdPersonId,
 	        	type: 'DELETE',
 	        	async: false,
 	        	dataType: 'json',
 	        	success : function(result) {
 	        		// alert(result.result);
 	                location.reload();
 	            },
 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
                	alert("Status: " + textStatus); 
                	alert("Error: " + errorThrown);
 				}
 	        });
 		}
 		
 		function UpdatePerson(censusId, censusHouseholdId, censusHouseholdPersonId) {
 			var data = 
 				'{ ' + 
 				'"personId": "' + $("#idInput").val() + '",' +
 				'"name": "' + $("#nameInput").val() + '",' +
 				'"relationshipToHead": "' + $("#relationshipToHeadInput").val() + '",' +
 				'"age": "' + $("#ageInput").val() + '",' +
 				'"status": "' + $("#statusInput").val() + '",' +
 				'"occupation": "' + $("#occupationInput").val() + '",' +
 				'"birthplace": "' + $("#birthplaceInput").val() + '"' +
				'}';

			alert(data);
			
 			$.ajax({
 	        	url : '../../../census/' + censusId + '/censushousehold/' + censusHouseholdId + '/person/' + censusHouseholdPersonId,
 	           	type: 'POST',
 	        	data: data,
 	        	dataType: 'json',
 	        	contentType: 'text/html',
 	            success: function(result) {
 	                alert(result);
 	                location.reload();
 	            },
 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
                	alert("Status: " + textStatus); 
                	alert("Error: " + errorThrown);
 				}
 	        });
 			
 			$('#personForm').hide();
 			$('#householdTable').show();
 		}
	</script>
</head>
<body>
	<a href="../../../index">Main Menu</a>

	<h1>Census Household</h1>
	
	<table>
		<tr>
			<td class='header'>Census</td>
			<td>${census.title}</td>
		</tr>
		<tr>
			<td class='header'>Address</td>
			<td>${censusHousehold.address}</td>
		</tr>
		<tr>
			<td class='header'>Reference</td>
			<td>${censusHousehold.reference}</td>
		</tr>
		<tr>
			<td class='header'>Notes</td>
			<td>${censusHousehold.notes}</td>
		</tr>
	</table>
	<br />
	<div id="householdTable">
		<table>
			<thead>
	    		<tr>
	      			<th>Name</th>
	      			<th>Rel.</th>
	      			<th>Age</th>
	      			<th>Status</th>
	      			<th>Occupation</th>
	      			<th>Birthplace</th>
	      			<th colspan='2' />
	    		</tr>
	  		</thead>		
	  		<tbody>		
	  		<c:forEach items="${censusHouseholdEntries}" var="entry">
			<tr>
				<c:if test="${entry.personId > 0}">
					<td id="${entry.censusHouseholdPersonId}_nameCell"><a href="../../../person/${entry.personId}">${entry.name}</a></td>
				</c:if>
				<c:if test="${entry.personId == 0}">
					<td id="${entry.censusHouseholdPersonId}_nameCell">${entry.name}</td>
				</c:if>
				<td id="${entry.censusHouseholdPersonId}_relationshipToHeadCell">${entry.relationshipToHead}</td>
				<td id="${entry.censusHouseholdPersonId}_ageCell">${entry.age}</td>
				<td id="${entry.censusHouseholdPersonId}_statusCell">${entry.status}</td>
				<td id="${entry.censusHouseholdPersonId}_occupationCell">${entry.occupation}</td>
				<td id="${entry.censusHouseholdPersonId}_birthplaceCell">${entry.birthplace}</td>
				
				<td class="gridbutton" onclick="editPerson(${census.id}, ${entry.censusHouseholdId}, ${entry.censusHouseholdPersonId}, ${entry.personId});">E</td>
				<td class="gridbutton" onclick="DeletePerson(${census.id}, ${entry.censusHouseholdId}, ${entry.censusHouseholdPersonId});">D</td>
			</tr>
		</c:forEach>
		</tbody>	
		</table>
		<br />
		<input type="button" id="addPersonButton" onclick="addPerson(${census.id}, ${censusHousehold.id});" value="Add Person" />
	</div>
	
	<div id="personForm">
		<table width="60%">
			<tr>
      			<td width="20%" class="header">Id</td>
      			<td>
      				<input type="text" id="idInput" size="30em" />
      			</td>
     		</tr>
			<tr>
      			<td width="20%" class="header">Name</td>
      			<td>
      				<input type="text" id="nameInput" size="80em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Rel.</td>
      			<td>
      				<input type="text" id="relationshipToHeadInput" size="30em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Age</td>
      			<td>
      				<input type="text" id="ageInput" size="30em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Status</td>
      			<td>
      				<input type="text" id="statusInput" size="30em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Occupation</td>
      			<td>
      				<input type="text" id="occupationInput" size="80em" />
      			</td>
     		</tr>
			<tr>
      			<td class="header">Birthplace</td>
      			<td>
      				<input type="text" id="birthplaceInput" size="80em" />
      			</td>
     		</tr>
		</table>
		<br />
		<input type="button" id="saveEditPersonButton" value="Save" />
		&nbsp;
		<input type="button" id="cancelEditPersonButton" value="Cancel" onclick="$('#personForm').hide(); $('#householdTable').show();"  />
	</div>
</body>
</html>
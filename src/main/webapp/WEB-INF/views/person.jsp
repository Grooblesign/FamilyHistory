<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<c:url value="/resources/stylesheet.css" />" type="text/css" />
		<title>Person</title>

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
	
		<script type="text/javascript">
	 		$(document).ready(function(){
	        	$("#eventDiv").hide();
	    	});
	 		
	 		function editEvent(eventId) {
	 			
	 			$('#mainDiv').hide();
	 			
	 			var citationId = 0;
	 			var sourceId = 0;

	 			$.ajax({
	 	        	url : '/FamilyHistory/event/' + eventId,
	 	        	async: false,
	 	        	dataType: 'json',
	 	        	type: 'GET',
	 	        	success : function(result) {
	 	 	 			$("#eventInput").val(result.type);
	 	 	 			$("#dateInput").val(result.date);
	 	 	 			$("#locationInput").val(result.location);
	 	 	 			$("#detailsInput").val(result.details);
	 	 	 			
	 	 	 			citationId = result.citationId;
	 	            },
	 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                	alert("Status: " + textStatus + " Error: " + errorThrown);
	 				}
	 	        });
	 			
	 			if (citationId > 0) {
		 			$.ajax({
		 	        	url : '/FamilyHistory/citation/' + citationId,
		 	        	async: false,
		 	        	dataType: 'json',
		 	        	type: 'GET',
		 	        	success : function(result) {
		 	 	 			$("#citationInput").val(result.details);
		 	 	 			
		 	 	 			sourceId = result.sourceId;
		 	 	 			
		 	 	 			if (sourceId > 0) {
		 	 	 				$("#sourceSelect").val(sourceId);
		 	        		}
		 	            },
		 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
		                	alert("Status: " + textStatus + " Error: " + errorThrown);
		 				}
		 	        });
	 			}
	 			
	 			$("#saveEventButton").unbind('click').click(function() { updateEvent(eventId); });
		 			 
	 			$('#eventDiv').show();
	 		}
	 		
	 		function updateEvent(eventId) {
	 			var data = 
	 				'{ ' + 
	 				'"eventId": "' + eventId + '",' +
	 				'"date": "' + $("#dateInput").val() + '",' +
	 				'"location": "' + $("#locationInput").val() + '",' +
	 				'"details": "' + $("#detailsInput").val() + '"' +
					'}';
	 			
	 			$.ajax({
	 	        	url : '/FamilyHistory/event/' + eventId,
	 	        	async: false,
	 	        	data: data,
	 	        	dataType: 'json',
	 	        	type: 'PUT',
	 	        	success : function(result) {
	 	        		location.reload();
	 	            },
	 				error: function(XMLHttpRequest, textStatus, errorThrown) { 
	                	alert("Status: " + textStatus + " Error: " + errorThrown);
	 				}
	 	        });
	 		}
 		</script>
	</head>
	<body>
		<a href="../index">Main Menu</a>
	
		<h1>${person.forenames}&nbsp;${person.surname}</h1>
		
		<div id="eventDiv">
			<h2>Event</h2>
			<table width="60%">
				<tr>
	      			<td width="20%" class="header">Event</td>
	      			<td>
	      				<input type="text" id="eventInput" size="50em" />
	      			</td>
	     		</tr>
				<tr>
	      			<td width="20%" class="header">Date</td>
	      			<td>
	      				<input type="text" id="dateInput" size="50em" />
	      			</td>
	     		</tr>
				<tr>
	      			<td width="20%" class="header">Location</td>
	      			<td>
	      				<input type="text" id="locationInput" size="100em" />
	      			</td>
	     		</tr>
				<tr>
	      			<td width="20%" class="header">Details</td>
	      			<td>
	      				<input type="text" id="detailsInput" size="100em" />
	      			</td>
	     		</tr>
				<tr>
	      			<td width="20%" class="header">Source</td>
	      			<td>
						<select id="sourceSelect">
						<c:forEach items="${sources}" var="source">
  							<option value="${source.id}">${source.title}</option>
						</c:forEach>
						</select>
	      			</td>
	     		</tr>
				<tr>
	      			<td width="20%" class="header">Citation</td>
	      			<td>
	      				<input type="text" id="citationInput" size="100em" />
	      			</td>
	     		</tr>
	     	</table>
			<br />
			<input type="button" id="saveEventButton" value="Save" />
			&nbsp;
			<input type="button" id="cancelEventButton" value="Cancel" onclick="$('#eventDiv').hide(); $('#mainDiv').show();"  />
		</div>
		
		<div id="mainDiv">
			<h2>Parents</h2>
			<table>
				<tr>
					<td class='header'>Father</td>
					<td>
						<c:if test="${person.father != null}">
							<a href="../person/${person.father.id}">${person.father.forenames}&nbsp;${person.father.surname}</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class='header'>Mother</td>
					<td>
						<c:if test="${person.mother != null}">
							<a href="../person/${person.mother.id}">${person.mother.forenames}&nbsp;${person.mother.surname}</a>
						</c:if>
					</td>
				</tr>
			</table>
			
			<h2>Events</h2>
			<table>
				<thead>
		    		<tr>
		      			<th>Date</th>
		      			<th>Event</th>
		      			<th>Location</th>
		      			<th>Details</th>
		      			<th />
		    		</tr>
		  		</thead>		
		  		<tbody>
			
			<c:forEach items="${events}" var="event">
				<tr>
					<td id="event_${event.id}_dateCell">${event.date}</td>
					<td id="event_${event.id}_eventCell">${event.type}</td>
					<td id="event_${event.id}_locationCell">${event.location}</td>
					<td id="event_${event.id}_detailsCell">${event.details}</td>
					<td class="gridbutton" onclick="editEvent(${event.id});">E</td>
				</tr>
			</c:forEach>
				</tbody>
			</table>
				
			<h2>Census</h2>
			<table>
				<thead>
		    		<tr>
		      			<th>Date</th>
		      			<th>Census</th>
		      			<th>Address</th>
		      			<th>Name</th>
		      			<th>Age</th>
		      			<th>Occupation</th>
		      			<th></th>
		    		</tr>
		  		</thead>		
		  		<tbody>
		  	<c:forEach items="${censusEntries}" var="censusEntry">
				<tr>
					<td>${censusEntry.date}</td>
					<td>${censusEntry.title}</td>
					<td>${censusEntry.address}</td>
					<td>${censusEntry.name}</td>
					<td>${censusEntry.age}</td>
					<td>${censusEntry.occupation}</td>
					<td>
						<a href="../census/${censusEntry.censusId}/censushousehold/${censusEntry.censusHouseholdId}">V</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		
			<h2>Marriages</h2>
			<table>
				<thead>
		    		<tr>
		      			<th>Date</th>
		      			<th>Spouse</th>
		      			<th>Location</th>
		    		</tr>
		  		</thead>		
		  		<tbody>	
			<c:forEach items="${marriages}" var="marriage">
				<tr>
					<td>
						${marriage.date}
					</td>
					<td>
						<c:if test="${marriage.husband != null}">
							<a href="../person/${marriage.husband.id}">${marriage.husband.fullname}</a>
						</c:if>
						<c:if test="${marriage.wife != null}">
							<a href="../person/${marriage.wife.id}">${marriage.wife.fullname}</a>
						</c:if>
					</td>
					<td>
						${marriage.location}
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			
			<c:if test="${children != null}">
			<h2>Children</h2>
			<table>
				<thead>
		    		<tr>
		      			<th>Birth Date</th>
		      			<th>Name</th>
		      			<th>Other Parent</th>
		    		</tr>
		  		</thead>		
		  		<tbody>	<c:forEach items="${children}" var="child">
				<tr>
					<td>${child.birthDate}</td>
					<td>
						<a href="../person/${child.id}">${child.forenames}&nbsp;${child.surname}</a>
					</td>
					<td>
						<c:if test="${person.gender == 'M'}">
							<a href="../person/${child.mother.id}">${child.mother.fullname}</a>
						</c:if>
						<c:if test="${person.gender == 'F'}">
							<a href="../person/${child.father.id}">${child.father.fullname}</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
			</c:if>
		</div>	
	</body>
</html>
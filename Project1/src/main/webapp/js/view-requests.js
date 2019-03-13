$(function() {
	loggedInFunctions();
	updateTable();
});

function updateTable() {
	$('#myTableId tbody').empty();
	requestByStatusServlet((data, status) => {
		table(data);
	});
}

function table(data) {
	let trHTML = '';
	
	$.each(data, function(i, item) {
		trHTML += '<tr><td class="requestID">';
		trHTML += item.requestID + '</td><td>';
		trHTML += '<button type="button" class="btn btn-primary viewEmployee">View</button>' + '</td><td>';
		trHTML += item.amount + '</td><td>';
		trHTML += item.status + '</td><td>';
		trHTML += '<button type="button" class="btn btn-primary viewEvent">View</button>' + '</td><td>';
		trHTML += item.justification + '</td><td>';
		trHTML += '<button type="button" class="btn btn-success approve">Approve</button><span class="paddRight5px"></span><button type="button" class="btn btn-danger deny">Deny</button>';
		trHTML += '</td></tr>';
	});
	
	$('#bodyTable').append(trHTML);
	
	$('.viewEmployee').click(function() {
		let requestID = $(this).closest("tr").find(".requestID").text();
		
		let data = {
			"id": requestID
		};
		
		getEmployeePost(data, (data, status) => {
			let text = '';

			text += '<h6>Employee ID: ' + data.employeeId + '</h6>';
			text += '<h6>First Name: ' + data.firstName + '</h6>';
			text += '<h6>Last Name: ' + data.lastName + '</h6>';
			text += '<h6>Street Address: ' + data.streetAddress + '</h6>';
			text += '<h6>City: ' + data.city + '</h6>';
			text += '<h6>State: ' + data.stateName + '</h6>';
			text += '<h6>Supervisor ID: ' + data.supervisorId + '</h6>';
			text += '<h6 class="margBot0">Employee Type: ' + data.employeeType + '</h6>';
			
			modal("Event Information", text);
		});
	});
	
	$('.viewEvent').click(function() {
		let requestID = $(this).closest("tr").find(".requestID").text();
	
		let data = {
			"id": requestID
		};
		
		getEventServlet(data, (data, status) => {
			let text = '';

			text += '<h6>Event ID: ' + data.eventId + '</h6>';
			text += '<h6>Event Type: ' + data.eventType + '</h6>';
			text += '<h6>Event Start Time: ' + data.eventDate + '</h6>';
			text += '<h6>Event End Time: ' + data.eventTime + '</h6>';
			text += '<h6>Event Location: ' + data.eventLocation + '</h6>';
			text += '<h6>Event Description: ' + data.eventDescription + '</h6>';
			text += '<h6>Event Status: ' + data.eventStatus + '</h6>';
			
			text += '<h6>Grading Format ID: ' + data.gradingFormat.gradingFormatId + '</h6>';
			text += '<h6>Grading Scale: ' + data.gradingFormat.gradingScale + '</h6>';
			text += '<h6 class="margBot0">Pass Condition: ' + data.gradingFormat.passCondition + '</h6>';
			
			modal("Event Information", text);
		});
	});
	
	$('.approve').click(function() {
		let requestID = $(this).closest("tr").find(".requestID").text();
		
		let data = {
			"approved": true,
			"id": requestID
		};
		
		approveOrDenayServlet(data, (data, status) => {
			let text = 'Request ' + requestID + ' was approved!';
			
			modal("Approved", text);
			updateTable();
		});
	});
	
	$('.deny').click(function() {
		let requestID = $(this).closest("tr").find(".requestID").text();
		
		let data = {
			"approved": false,
			"id": requestID
		};
		
		approveOrDenayServlet(data, (data, status) => {
			let text = 'Request ' + requestID + ' was denied!';
			
			modal("Denied", text);
			updateTable();
		});
	});
}
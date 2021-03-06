$(function() {
	loggedInFunctions();	
	displayEmployee();
	updateTable();
	displayMessages();
});

function displayMessages() {
	getMessageServlet((data, status) => {
		console.log(data);
		if (data.length != 0) {
			let msg = '';
			$.each(data, function (i, item) {
			    msg += '<h6>Message ' + (i + 1) + ': One of your reimbursement requests'
			    	+ ' was denied for the following reason: ' + item.message + '</h6>';
			    deleteMessageServlet(item, (data, status) => {
					console.log(data);
				});
			});
			modal("Messages", msg);
		}
	});
}

function displayEmployee() {
	getEmployee((data, status) => {
		$('#employeeId').html(data.employeeId);
		$('#firstName').html(data.firstName);
		$('#lastName').html(data.lastName);
		$('#streetAddress').html(data.streetAddress);
		$('#city').html(data.city);
		$('#stateName').html(data.stateName);
		$('#supervisorId').html(data.supervisorId);
		$('#employeeType').html(data.employeeType);
		$('#remainingBenefit').html(data.remainingBenefit);
	});
}

function updateTable() {
	$('#myTableId tbody').empty();
	getRequestByEmployeeIDServlet((data, status) => {
		table(data);
	});
}

function table(data) {
	let trHTML = '';
	
	$.each(data, function(i, item) {
		trHTML += '<tr><td class="requestID">';
		trHTML += item.requestID + '</td><td>';
		trHTML += item.amount + '</td><td class="status">';
		trHTML += item.status + '</td><td>';
		trHTML += '<button type="button" class="btn btn-primary viewEvent">View</button>' + '</td><td>';
		trHTML += item.justification + '</td><td>';
		trHTML += '<button type="button" class="btn btn-success complete">Complete</button>';
		trHTML += '</td></tr>';
	});
	
	$('#bodyTable').append(trHTML);
	
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
			text += '<h6>Pass Condition: ' + data.gradingFormat.passCondition + '</h6>';
			text += '<h6 class="margBot0">Grade: ' + data.grade + '</h6>';
			
			modal("Event Information", text);
		});
	});
	
	$('.complete').click(function() {		
		let requestID = $(this).closest("tr").find(".requestID").text();
		let status = $(this).closest("tr").find(".status").text();
		
		if (status == "pending completion") {
			var msg = prompt("Please provide your grade for this course or the status of your presentation.");
			
			if (msg == null) {
				return;
			} else if (msg == "") {
				msg = "No message."
			}
			
			let requestID = $(this).closest("tr").find(".requestID").text();
			let id = {
					"id": requestID
				};
			getEventServlet(id, (data, status) => {
				data.grade = msg;
				
				updateEvent(data, (data, status) => {
					console.log(data);
				});
			});
			
			let data = {
				"id": requestID
			};
			
			completeRequestServlet(data, (data, status) => {
				let text = 'Request completed, waiting for benefit coordination confirmation.';
				
				modal("Approved", text);
				updateTable();
			});
		} else if (status == "pending confirmation") {
			let text = 'Request ' + requestID + ' event is already completed.';
			
			modal("Denied", text);
			updateTable();
		} else {			
			let text = 'Request ' + requestID + ' must be approved before you can complete it.';
			
			modal("Denied", text);
			updateTable();
		}
	});
}
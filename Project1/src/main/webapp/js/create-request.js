$(function() {
	loggedInFunctions();
	populateSelect();
});

function populateSelect() {
	getAllFormatsServlet((data, status) => {
		$.each(data, function (i, item) {
		    $('#gradingScale').append($('<option>', {
		        text: item 
		    }));
		});
	});
}

function requestForm() {
	let eventType = $('#eventType').val();
	let gradingScale = $('#gradingScale').val();
	let startDate = $('#startDate').val();
	let endDate = $('#endDate').val();
	let eventLocation = $('#eventLocation').val();
	let amount = $('#amount').val();
	let eventDescription = $('#eventDescription').val();
	let justification = $('#justification').val();
	
	let data = {
		"event_type": eventType,
		"grading_scale": gradingScale,
		"start_date": startDate,
		"end_date": endDate,
		"event_location": eventLocation,
		"amount": amount,
		"description": eventDescription,
		"justification": justification,
	}
	
	createRequestServlet(data, (data, status) => {
		if(data == 'Request created!')
		{
			let text = 'Request created successfully.';
			
			modal("Confirmation", text);
			updateTable();
		}
		else
		{
			let text = 'The requested cost is higher than your remaining benefit.';
			
			modal("Error", text);
			updateTable();
		}
	});
}
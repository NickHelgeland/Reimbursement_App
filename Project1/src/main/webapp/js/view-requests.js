$(function() {
	loggedInFunctions();

	requestByStatusServlet((data, status) => {
		table(data);
	});
});

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
		console.log($(this).closest("tr").find(".requestID").text());
	});
	
	$('.viewEvent').click(function() {
		let requestID = $(this).closest("tr").find(".requestID").text();
	
		let data = {
			"id": requestID
		};
		
		getEventServlet(data, (data, status) => {
			console.log(data);
		});
	});
	
	$('.approve').click(function() {
		console.log($(this).closest("tr").find(".requestID").text());
	});
	
	$('.deny').click(function() {
		console.log($(this).closest("tr").find(".requestID").text());
	});
}
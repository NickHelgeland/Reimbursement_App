$(function() {
	loggedInFunctions();

	requestByStatusServlet((data, status) => {
		table(data);
		
		console.log(data);
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
		trHTML += item.justification;
		trHTML += '</td></tr>';
	});
	
	$('#bodyTable').append(trHTML);
	
	$('.viewEmployee').click(function(){
		console.log($(this).closest("tr").find(".requestID").text());
	});
	
	$('.viewEvent').click(function(){
		console.log($(this).closest("tr").find(".requestID").text());
	});
}
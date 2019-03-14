$(function() {
	loggedInFunctions();
	populateSelect();
});

function populateSelect() {
	getAllFormatsServlet((data, status) => {
		$.each(data, function (i, item) {
		    $('#gradingScale').append($('<option>', {
		        text: item.gradingScale 
		    }));
		});
	});
}
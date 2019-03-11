$(function() {
	$('#navbar').load('/Project1/html/navbar.html');
	$('#footer').load('/Project1/html/footer.html');
	displayEmployee();
});

function displayEmployee() {
	getEmployee((data, status) => {
		console.log(data);
	});
}
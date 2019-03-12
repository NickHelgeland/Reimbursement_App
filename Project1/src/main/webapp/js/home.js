$(function() {
	$('#navbar').html(navbar());
	$('#footer').load('/Project1/html/footer.html');
	logOffBtn();
	displayEmployee();
});

function displayEmployee() {
	getEmployee((data, status) => {
		console.log(data);
		$('#employeeId').html(data.employeeId);
		$('#firstName').html(data.firstName);
		$('#lastName').html(data.lastName);
		$('#streetAddress').html(data.streetAddress);
		$('#city').html(data.city);
		$('#stateName').html(data.stateName);
		$('#supervisorId').html(data.supervisorId);
		$('#employeeType').html(data.employeeType);
	});
}
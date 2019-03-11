$(function() {
	$('#navbar').html(aaa);
	$('#footer').load('/Project1/html/footer.html');
	logOffBtn();
	displayEmployee();
});

function aaa() {
	text = '';
	text += '<nav class="navbar navbar-expand-lg bgColorBlack navbar-dark fixed-top"><div class="container"> <a class="navbar-brand" href="/Project1/home">Cloud9 TRMS</a> <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar"> <span class="navbar-toggler-icon"></span> </button><div class="collapse navbar-collapse" id="collapsibleNavbar"><ul class="navbar-nav mr-auto"><li class="nav-item"> <a class="nav-link" href="/sounds">SOUNDS</a></li></ul><ul class="navbar-nav"><li class="nav-item"><div class="nav-link"> <button type="button" class="btn btn-primary" id="logOffBtn">Log Off</button></div></li></ul></div></div> </nav>';
	return text;
}

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
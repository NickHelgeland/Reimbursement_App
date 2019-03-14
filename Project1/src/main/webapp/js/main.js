// get/post calls
function get(url, func) {	
	$.get(url, func);
}

function post(url, data, func) {
	$.post(url, JSON.stringify(data), func);
}

// api calls
function getRequestByEmployeeIDServlet(func) {
	url = '/Project1/api/request-by-employee-id';	
	get(url, func);
}

function getEmployee(func) {
	url = '/Project1/api/get-employee';	
	get(url, func);
}

function getAllFormatsServlet(func) {
	url = '/Project1/api/get-all-formats';	
	get(url, func);
}

function getEmployeePost(data, func) {
	url = '/Project1/api/get-employee';	
	post(url, data, func);
}

function approveOrDenayServlet(data, func) {
	url = '/Project1/api/approve-or-deny';	
	post(url, data, func);
}

function logOff() {
	url = '/Project1/api/logout';
	get(url, (data, status) => {});
}

function currentSession(func) {
	url = '/Project1/api/current-session';
	get(url, func);
}

function requestByStatusServlet(func) {
	url ='/Project1/api/request-by-status';
	get(url, func);
}

function getEmployeeList(func) {
	url = '/Project1/api/get-employee-list';	
	get(url, func);
}

function login(data, func) {
	let url = '/Project1/api/login';	
	post(url, data, func);
}

function getEventServlet(data, func) {
	let url = '/Project1/api/get-event';	
	post(url, data, func);
}

function getEmployeeType(func) {
	url = '/Project1/api/get-type';	
	get(url, func);
}

// app wide functions
function logOffBtn() {	
	$('#logOffBtn').click(function() {
		logOff();		
		alert("You have logged off.");
		window.location.href = "/Project1";
	});
}

function loggedInFunctions() {
	navbar();
	$('#footer').load('/Project1/html/footer.html');
}

(function() {
	'use strict';
	window.addEventListener('load', function() {
		var forms = document.getElementsByClassName('needs-validation');
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
				if (form.checkValidity() === false) {
					form.classList.add('was-validated');
					event.preventDefault();
					event.stopPropagation();
				} else {
					event.preventDefault();
					event.stopPropagation();
					
					// what form
					var whatForm = $('.needs-validation');
					
					if (whatForm.is('#loginForm')) {
						loginForm();
					}					
					
					// clear the form
					form.classList.remove('was-validated');
					form.reset();
				}
			}, false);
		});
	}, false);
})();

// reuse html
function modal(heading, body) {	
	text = '';
	
	text += '<div class="modal fade" id="myModal">';
	text += '	<div class="modal-dialog modal-dialog-centered">';
	text += '		<div class="modal-content">';
	text += '			<div class="modal-header">';
	text += '				<h4 class="modal-title">' + heading + '</h4>';
	text += '				<button type="button" class="close" data-dismiss="modal">&times;</button>';
	text += '			</div>';
	text += '			<div class="modal-body">' + body + '</div>';
	text += '			<div class="modal-footer">';
	text += '				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>';
	text += '			</div>';
	text += '		</div>';
	text += '	</div>';
	text += '</div>';
		
	$('#ourModal').html(text);
	$("#myModal").modal('show');
}

function navbar() {		
	getEmployeeType((data, status) => {
		text = '';
		
		text += '<nav class="navbar navbar-expand-lg bgColorBlack navbar-dark fixed-top">';
		text += '	<div class="container">';
		text += '		<a class="navbar-brand" href="/Project1/home">CLOUD9 TRMS</a>';
		text += '		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">';
		text += '			<span class="navbar-toggler-icon"></span>';
		text += '		</button>';
		text += '		<div class="collapse navbar-collapse" id="collapsibleNavbar">';
		text += '			<ul class="navbar-nav mr-auto">';
		text += '				<li class="nav-item">';
		text += '					<a class="nav-link" href="/Project1/create-request">CREATE REQUEST</a>';
		text += '				</li>';
		
		if (data != 'employee') {
			text += '			<li class="nav-item">';
			text += '				<a class="nav-link" href="/Project1/view-requests">VIEW REQUESTS</a>';
			text += '			</li>';
		}
		
		text += '			</ul>';
		text += '			<ul class="navbar-nav">';
		text += '				<li class="nav-item">';
		text += '					<div class="nav-link">';
		text += '						<button type="button" class="btn btn-primary" id="logOffBtn">Log Off</button>';
		text += '					</div>';
		text += '				</li>';
		text += '			</ul>';
		text += '		</div>';
		text += '	</div>';
		text += '</nav>';
		
		$('#navbar').html(text);
		
		logOffBtn();
	});
}
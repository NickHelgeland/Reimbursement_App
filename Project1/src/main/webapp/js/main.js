// get/post calls
function get(url, func) {	
	$.get(url, func);
}

function post(url, data, func) {
	$.post(url, JSON.stringify(data), func);
}

// api calls
function getEmployee(func) {
	url = '/Project1/api/get-employee';	
	get(url, func);
}

function logOff() {
	url = '/Project1/api/logout';
	get(url, (data, status) => {});
}

function currentSession(func) {
	url = '/Project1/api/current-session';
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

// app wide functions
function logOffBtn() {	
	$('#logOffBtn').click(function() {
		logOff();		
		alert("You have logged off.");
		window.location.href = "/Project1";
	});
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
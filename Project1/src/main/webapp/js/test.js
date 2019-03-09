$(function() {
//	getEmployee();
//	getEmployeeList();
	button()
});

function get(url, func) {	
	$.get(url, func);
}

function post(url, data, func) {
	$.post(url, JSON.stringify(data), func);
}

function getEmployee() {
	url = '/Project1/api/get-employee';	
	get(url, (data, status) => {
		console.log(data);
	});
}

function getEmployeeList() {
	url = '/Project1/api/get-employee-list';	
	get(url, (data, status) => {
		console.log(data);
	});
}

function button() {
	$("#button").click(function(){
		login();
	}); 
}

function login() {
	url = '/Project1/api/login';
	data = {
		"username": "TheDude",
		"password": "password"
	};
	post(url, data, (data, status) => {
		console.log(data);
	});
}

//function testPost() {
//	url = '/TestServlet';
//	data = {
//		"key1": "hello world"
//	};
//	
//	post(url, data);
//}

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

	          // get form info
	          let username = $('#username').val();
	          let password = $('#password').val();

	          // ajax call
		      	url = '/Project1/api/login';
		    	data = {
		    		"username": username,
		    		"password": password
		    	};
		    	post(url, data, (data, status) => {
		    		console.log(data);
		    	});


	          // clear the form
	          form.classList.remove('was-validated');
	          form.reset();
	        }
	      }, false);
	    });
	  }, false);
	})();
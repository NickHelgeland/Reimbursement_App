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
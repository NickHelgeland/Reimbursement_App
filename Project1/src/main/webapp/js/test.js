$(function() {
	getEmployee();
	getEmployeeList();
});

function get(url) {	
	$.get(url, function(data, status) {
		console.log(data);
	});
}

//function post(url, data, func) {	
//	$.post(url, JSON.stringify(data), function(data, status) {
//		console.log(data);
//	});
//}

function getEmployee() {
	url = '/Project1/api/get-employee';	
	get(url);
}

function getEmployeeList() {
	url = '/Project1/api/get-employee-list';	
	get(url);
}

//function testPost() {
//	url = '/TestServlet';
//	data = {
//		"key1": "hello world"
//	};
//	
//	post(url, data);
//}
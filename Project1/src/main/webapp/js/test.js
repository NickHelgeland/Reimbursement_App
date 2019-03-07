$(function() {
	testPost();
});

function get(url) {	
	$.get(url, function(data, status) {
		console.log(data);
	});
}

function post(url, data) {	
	$.post(url, JSON.stringify(data), function(data, status) {
		console.log(data);
	});
}

function testGet() {
	url = '/TestServlet';
	
	get(url);
}

function testPost() {
	url = '/TestServlet';
	data = {
		"key1": "hello world"
	};
	
	post(url, data);
}
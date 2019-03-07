$(function() {
	testGet();
});

function get(url) 
{	
	$.get(url, function(data, status) 
	{
		console.log(data);
		document.getElementById("header").innerHTML = data[0].firstName;
	});
}

function post(url, data) 
{	
	$.post(url, JSON.stringify(data), function(data, status) 
	{
		console.log(data);
	});
}

function testGet() 
{
	url = '/TestServlet';
	
	get(url);
}

function testPost() 
{
	url = '/TestServlet';
	data = 
	{
		"key1": "hello world"
	};
	
	post(url, data);
}
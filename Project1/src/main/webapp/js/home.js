$(function() {
	displayEmployee();
});

function displayEmployee() {
	getEmployee((data, status) => {
		console.log(data);
	});
}
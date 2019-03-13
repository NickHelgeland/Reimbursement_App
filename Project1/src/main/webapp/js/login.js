function loginForm() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	let data = {
		"username": username,
		"password": password
	};
	
	login(data, (data, status) => {
		if (data == "") {
			window.location.href = "/Project1/home";
		} else {
			modal("Failure", data);
		}
	});
}
function login() {
	$.ajax({
		type : "GET",
        dataType : "json",
		url  : "http://192.168.32.128:8181/cxf/service",
		success: function(result) {
			if (result == null || result == "") {
				return;
			}
			var str = JSON.stringify(result)
			window.localStorage.setItem("res",str)
            window.location.href = "test.html";
		}
	});
}


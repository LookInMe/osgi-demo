function TestLog() {
	$.ajax({
		type : "GET",
        dataType : "json",
		url  : "http://192.168.32.128:8181/cxf/service",
		success: function(result) {
			if (result == null || result == "") {
				return;
			}
			
			json = eval(result);
			alert(json.toString());
		}
	});		
}
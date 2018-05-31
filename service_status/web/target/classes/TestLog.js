function TestLog() {
	$.ajax({
		type : "GET",
        dataType : "json",
		url  : "http://192.168.32.128:8181/cxf/service",
		success: function(result) {
			if (result == null || result == "") {
				return;
			}

			var json = "<div>flow:"+result.flow+"</dev><br><dev>xj:"+result.xj+"</dev><br><dev>xn:"+result.xn+"</dev>"
			$("body").html(json)
		}
	});		
}
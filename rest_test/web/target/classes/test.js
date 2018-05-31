function show() {
    var result = window.localStorage.getItem('res');
    var json = eval('(' + result + ')')
    if (json.flow == 1)
        document.getElementById('flow').style.display = 'block';
    if (json.xj == 1)
        document.getElementById('xj').style.display = 'block';
    if (json.zy == 1)
        document.getElementById('zy').style.display = 'block';
}

$(function () {
    $('#flow').click(function () {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "http://192.168.32.128:8181/cxf/flow/all",
            success: function (result) {
                if (result == null || result == "") {
                    return;
                }
                alert(result)
            }
        });
    })
    $('#xj').click(function () {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "http://192.168.32.128:8181/cxf/xj/all",
            success: function (result) {
                if (result == null || result == "") {
                    return;
                }
                alert(result)
            }
        });
    })
    $('#zy').click(function () {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "http://192.168.32.128:8181/cxf/zy/all",
            success: function (result) {
                if (result == null || result == "") {
                    return;
                }
                alert(result)
            }
        });
    })
})
/*S websocket*/
//原生WebSocket
var websocket = null;
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://127.0.0.1:8081/sell/webSocket");
} else {
    tip("亲，该浏览器不支持WebSocket呢~")
}
websocket.onmessage = function (event) {
    $("#myModal").modal('show');
    $(".modal-body").html(event.data);
    document.getElementById("notice").play();
}
websocket.onopen = function (event) {
    console.log("打开websocket")
}
websocket.onclose = function (event) {
    console.log("关闭websocket")
}
websocket.onerror = function (error) {
    console.log("websocket发生错误" + error.message)
}
window.onbeforeunload = function () {
    websocket.close();
}
/*E websocket*/


function ajax(url, type, data) {
    var that = this;
    var typ = type || "GET";

    $.ajax({
        type: typ,
        data: data,
        dataType: "json",
        url: url,
        success: function (data) {
            that.tip(data.msg);
            if (data.code == 0) {
                setTimeout(function () {
                    window.location.reload();
                }, 2000);
            }
        },
        error: function (error) {
            that.tip(error.message);
        }
    });
}

function tip(msg) {
    var tip = $("#tip")
    tip.html(msg);
    tip.css("display", "block");
    setTimeout(function () {
        tip.css("display", "none");
    }, 3000);
}
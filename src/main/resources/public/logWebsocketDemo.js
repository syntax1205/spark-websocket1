//Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/log");
webSocket.onclose = function () {
    alert("closed")
};
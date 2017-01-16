var conn = require('connect');

var app = conn().use(function(req, res){
  if (req.url == "/hello") {
    console.log("plain text");
    res.end("Hello, world");
  } else if (req.url == "/hello.json") {
    var data = {'message':'hello, world'};
	var jsonData = JSON.stringify(data);
	console.log("JSON string");
	res.setHeader('Content-Type','application/json');
    res.end(jsonData);
  } else if (req.url == "/favicon.ico") { // request the brower icon for the tabs
    res.statusCode = 204; // no content
	res.end();
  } else {
    res.statusCode = 500;
	res.end("Oops, server error.");
  } 
  // console.log(req.url);
}).listen(3456);

console.log('Connecting Server Port 3456');
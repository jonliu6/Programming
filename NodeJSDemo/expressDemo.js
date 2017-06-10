var express = require('express');
var app = express();

app.use(express.static(__dirname + '/public'));

app.get('/*', function(req, res){
  res.writeHead(404);
  res.end('Resource does not exist.');
});


var port = 3456;
app.listen(port, function(){
  console.log('Listening on port ' + port + '...');
});
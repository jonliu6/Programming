var url = require('url');

var testUrl = "http://username:password@localhost:8080/poweronremote/env?userType=admin&userGroup=2";

var urlObj = url.parse(testUrl, true);
console.log(urlObj);
var urlString = url.format(urlObj);
console.log(urlString);

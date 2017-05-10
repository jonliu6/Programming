// This is a simple Node JS program to use JSON.stringify() to format a JSON string.
var fs = require("fs");

var argCnt = process.argv.length;
if (argCnt < 3) {
    console.log("Usage: node " + process.argv[1] + " <JSON Filename>");
	process.exit(1);
}

var fname = process.argv[2];
var newFName = fname + ".new";
var jsonStr = "";
fs.readFile(fname, "utf8", function(err, data){
    if (err) {
	    throw err;
	}
	// console.log(JSON.stringify(JSON.parse(data), null, "    "));
	jsonStr = JSON.stringify(JSON.parse(data), null, "    ");
	fs.writeFile(newFName, jsonStr, function(err){
	    if (err) {
	        throw err;
	    }
		console.log(jsonStr);
		
		console.log("JSON file " + fname + " formatted.");
	});
});


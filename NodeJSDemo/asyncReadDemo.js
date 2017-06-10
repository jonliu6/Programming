var fs = require('fs');

console.log('Program started.');
fs.readFile('testFile.txt', function(err, data){
	if (err) {
		console.error(err);
		return;
	}
	console.log("Asynchronous read content: \n" + data.toString());
});

console.log('Program finished.');

var fs = require('fs');

console.log('Program started.');
var data = fs.readFileSync('testFile.txt');
console.log("Synchronous read content: \n" + data.toString());
console.log('Program finished.');

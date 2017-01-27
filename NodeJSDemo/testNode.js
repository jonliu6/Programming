var fs = require('fs');
var os = require('os');
var crypto = require('crypto');

console.log(os.userInfo());

console.log(__dirname);
console.log(__filename);
console.log(fs);
// console.log(crypto);
var password = "Password";
console.log("Original Password: " + password);
var encryptStream = crypto.createHash('md5').update(password).digest('hex');
var encryptStream1 = crypto.createHash('sha256').update(password).digest("base64"); // same as MD5????

console.log("Encrypted Password (MD5): " + encryptStream);
console.log("Encrypted Password: (SHA256)" + encryptStream);

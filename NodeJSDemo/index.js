console.log("This is the main JS.");
var toModule = require('./troubleOrder.js');
var to1 = toModule('1-111','new');
var to2 = toModule('1-222','dispatched');
console.log(to1.getOrder());
console.log('####################');
console.log(to2.getOrder());
console.log('####################');
console.log(to2);
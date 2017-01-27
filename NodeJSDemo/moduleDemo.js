var mgr = require('./Manager.js');
var emp = require('./Employee.js');

console.log(mgr.sampleMessage);
console.log(emp.sampleMessage);
mgr.delegate();
emp.answer();

mgr.plan();

emp.request(10);
mgr.approve();
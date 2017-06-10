module.exports.sampleMessage = 'Employee: I have a boss.';

module.exports.answer = function() {
  console.log('Employee: That depends...');
};

module.exports.request = function(xDays) {
  console.log('Employee: Can I take ' + xDays + ' day(s) off?');
};

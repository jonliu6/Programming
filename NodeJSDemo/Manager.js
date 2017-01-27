var manager = {
  sampleMessage: 'Manager: I\'m the boss.',
  delegate: function() {
    console.log('Manager: Can you help me do something?');
  },
  plan: function() {
    console.log('Manager: Please see your work plan in below...');
  },
  approve: function() {
    console.log('Manager: Approved.');
  } 
};

module.exports = manager; // export an object

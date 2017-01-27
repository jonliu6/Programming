'use strict';
var events = require('events');
var eventEmitter = new events.EventEmitter();

var lunchHandler = function heatUp() {
	console.log('Start heating your lunch...');
	console.log('... for several minutes...');
	eventEmitter.emit('ready_to_eat');
	// setTimeout(isReady, 2000); // delay 2 seconds
};

function isReady() {
	eventEmitter.emit('ready_to_eat');
	console.log('eating...');
}

eventEmitter.on('start_heating_lunch',lunchHandler);
eventEmitter.on('ready_to_eat',function(){
	console.log('enjoy your meal.');
});

eventEmitter.emit('start_heating_lunch');

console.log('eating...');

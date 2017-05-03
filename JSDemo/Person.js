function Person(firstName, lastName) {
	this.firstName = firstName,
	this.lastName = lastName
}

Person.prototype.getFullName = function() {
	return this.firstName + " " + this.lastName;
};

Person.prototype.changeFullName = function(firstName, lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
};

function changeFirstName(firstName){
	firstName = 'Hello';
}
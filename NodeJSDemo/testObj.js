function Person(n, a) {
  this.name = n;
  this.age = a;
}

function Student(n, a, s) {
  this.personInfo = Person;
  this.personInfo(n,a);
  this.school = s;
}

Student.prototype = new Person();
newStudent = new Person();
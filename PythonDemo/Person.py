import random

class Person:
    firstName = "Jane"
    lastName = "Doe"

    def __init__(self, fname, lname):
        self.firstName = fname
        self.lastName = lname

    def getFirstName(self):
        return self.firstName
    
    def setFirstName(self, fname):
        self.firstName = fname

    def getLastName(self):
        return self.lastName
    
    def setLastName(self, lname):
        self.lastName = lname

    def sayHello(self):
        print("Hello, I'm", self.firstName, self.lastName, ", how are you?")

class Student(Person):
    studentNo = 0
    maxNo = 10000

    def initStudentNo(self):
        if self.studentNo == 0 or self.studentNo is None:
            self.studentNo = random.randrange(Student.maxNo)
            print("New student#", self.studentNo)
        else:
            print("Existing student#", self.studentNo)

s = Student("John", "Smith")
s.sayHello()
s.initStudentNo()

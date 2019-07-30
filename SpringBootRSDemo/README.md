This project is a sample of Spring RESTful Web Services with JPA using Spring Boot.

Environment:
  - Open JDK 11
  - Wildfly 15.0.x or Tomcat 9.x

Test with cURL:
  curl -X GET http://localhost:9090/notes
  curl -X POST -H "Content-Type: application/json" -d "{"""title""":"""How to create SpringBoot RESTful web services""","""category""":"""Programming""","""subCategory""":"""SpringBoot""","""description""":"""1.Entity; 2. Repository; 3. RestController; 4. Application Configuration"""}" http://localhost:9090/addNote
  curl -X POST -H "Content-Type: application/json" -d "{"""title""":"""How to create SpringBoot JPA data services""","""category""":"""Programming""","""subCategory""":"""SpringBoot-Data""","""description""":"""1.Entity; 2. Repository; 3. RestController; 4. Application Configuration"""}" http://localhost:9090/addNote
  curl -X GET http://localhost:9090/notes/1
  curl -X PUT -H "Content-Type: application/json" -d "{"""title""":"""How to build a fence""","""category""":"""Home""","""subCategory""":"""Landscaping""","""description""":"""1.Cut the wood; 2. Nail the boards"""}" http://localhost:9090/notes/1
  curl -X DELETE http://localhost:9090/notes/2
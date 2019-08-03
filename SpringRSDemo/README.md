This project is a sample of Spring REST Web Services using Spring Web/MVC.

Environment:
  - Open JDK 11
  - Wildfly 15.0.x

Client Test Tool - cURL:
Test URLs:
  curl -X GET http://localhost:8080/SpringRSDemo/rs/noteRS/notes
  curl -X POST -H "Content-Type: application/json" -d "{"""title""":"""Cheese Cake Recipe""","""category""":"""Cook""","""subCategory""":"""Desert""","""description""":"""How to make a delicious cheese cake with low fat"""}" http://localhost:8080/SpringRSDemo/rs/noteRS/notes/add
  curl -X DELETE "http://localhost:8080/SpringRSDemo/rs/noteRS/notes/Book1"
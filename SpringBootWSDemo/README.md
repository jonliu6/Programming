This project is a sample of Spring Contract-first Web Services using Spring Boot.

Environment:
  - Open JDK 11
  - Wildfly 15.0.x or Tomcat 9.x

Test with cURL:
curl --header "content-type: text/xml" -d @soapRequestSample.xml http://localhost:8080/ws

Test with a C#.Net application in \WSClient.Net
- add service reference by the WSDL - http://localhost:8080/ws/noteWS.wsdl
- refer to the service and call note.getNotebyTitle
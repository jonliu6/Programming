This project is a sample of Spring Contract-first Web Services using Spring Boot.

Environment:
  - Open JDK 11
  - Wildfly 15.0.x or Tomcat 9.x

Test with cURL:
curl --header "content-type: text/xml" -d @soapRequestSample.xml http://localhost:8080/ws

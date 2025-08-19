SET PARENTPATH=C:\Workspace\GithubRepos\Programming\JAXWSDemo2Client\lib
SET CLASSPATH=%PARENTPATH%\jaxws-rt-4.0.3.jar;%PARENTPATH%\jaxb-xjc-4.0.5.jar;%PARENTPATH%\jaxws-tools-4.0.3.jar;%PARENTPATH%\jaxb-core-4.0.5.jar;%PARENTPATH%\jaxb-runtime-4.0.5.jar;%PARENTPATH%\istack-commons-runtime-4.2.0.jar;%PARENTPATH%\stax-ex-2.1.0.jar;%PARENTPATH%\jakarta.xml.bind-api-4.0.2.jar;%PARENTPATH%\jakarta.xml.ws-api-4.0.2.jar;%PARENTPATH%\jakarta.activation-api-2.1.3.jar;%PARENTPATH%\streambuffer-2.1.0.jar;%PARENTPATH%\txw2-20110809.jar;%CLASSPATH%

java -cp %CLASSPATH%;. com.sun.tools.ws.WsImport -keep -p org.freecode.demo -clientjar helloservice.jar http://localhost:8080/JAXWS/SayingHello?wsdl   

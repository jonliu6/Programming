# Getting Started

# Issue of javax.xml.bind.*, which is now changed to jakarta.xml.bind.*

# SSL Certificate and Keystore
# If your web service URL is https, you will need a SSL certificate from the server; otherwise, you will get an error - "javax.net.ssl.SSLHandshakeException: sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target"
# To get the SSL certificate, in browser to view the SSL cert and export it to \src\main\resources
# Then, use the follow Java command to import the certificate to the trust store - %JAVA_HOME%\lib\security\cacerts 
keytool -import -alias localhost -keystore cacerts -storepass changeit -file localhost.crt

# For further reference, please consider the following sections:
https://spring.io/guides/gs/consuming-web-service
https://www.jesperdj.com/2018/09/30/jaxb-on-java-9-10-11-and-beyond/
https://betterjavacode.com/programming/consuming-a-soap-webservice-over-https

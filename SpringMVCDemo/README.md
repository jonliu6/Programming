This is an Maven project demonstrate Model/View/Controller concepts by Spring MVC framework. Also, Bean validation is used in the model entities.

When the project was upgraded with Spring Framework 6.1, few updates in the code and configuration
- Multipart changed from Commons-fileupload to Spring Multipart
	- org.springframework.web.multipart.support.StandardServletMultipartResolver used, more configuration needed for file size etc.
	- <multipart-config /> added in web.xml with DispatcherServlet
- modelAttribute needs to specified in the form post - eg addBook.jsp
- Servlet API upgraded to Jakarta.servlet-api 6.1


Test URL:
Say Hello: http://<server>:<port>/<Context-path or warfile name>/controller/demo/sayHello
Say Hi to a name: http://<server>:<port>/<Context-path or warfile name>/controller/demo/sayHi?name=John
Add a new Book: http://<server>:<port>/<Context-path or warfile name>/controller/library/add

This project shows some examples from "The Spring Web MVC Framework" at skillport.com - delivered by Janani Ravi

sample URLs for the view pages:
  http://localhost:8080/SpringMVCFileUploadDownloadDemo/sayHello
  http://localhost:8080/SpringMVCFileUploadDownloadDemo/exception?name=sql
  http://localhost:8080/SpringMVCFileUploadDownloadDemo/article/create
  http://localhost:8080/SpringMVCFileUploadDownloadDemo/uploadForm
  
The project was originally built with Spring Framework 5, running on Wildfly Wildfly 15.0.1-Final, OpenJDK 11.0.2, Apache Maven 3.6.3
Then, it was upgraded Spring Framework 6.1.13 with Wildfly 30.0.1-Final, OpenJDK 21.0.2, Apache Maven 3.9.6

Couple of changes due to the upgrade:
  - Spring Framework 6.1.13
  - OpenJDK 21.0.2
  - jakarta.servlet-api 6.1.0 replaced javax.servlet-api 3.0.1
    -- change Servlet classes in the code as the following
        * jakarta.servlet.ServletContext
        * jakarta.servlet.http.HttpSession
        * jakarta.servlet.http.HttpServletRequest
        * jakarta.servlet.http.HttpServletResponse;
  - jakarta.servlet.jsp.jstl-api 3.0.2 and jakarta.servlet.jsp.jstl 3.0.1 replaced jstl 1.2
	-- <%@ taglib uri="jakarta.tags.core" prefix="c" %> and <%@ taglib uri = "jakarta.tags.functions" prefix = "fn" %> replaced taglib http://java.sun.com/jsp/jstl/core, and /functions
  - Spring StandardServletMultipartResolver implements MultipartResolver replaced Apache Commons-fileupload
    -- redefine in SpringMvcConfiguration
    -- initialize MultipartConfig in onStartup() of WebServletConfiguration
    -- create custom class FileMultipartFile implements MultipartFile
    -- in FileUploadController, remove @RequestParam for fileName since it's empty now - was okay with Spring 5 with Apache Commons-fileupload. Now, use getOriginalFilename() from MultipartFile
    
Remaining Issue:
  - ignorable errors probably due to some race condition when removing temp files:
    -- UT005005: Cannot remove uploaded file c:\Temp\undertow8321205884406019137upload
    -- UT010015: Could not delete file c:\Temp\undertow15293509536956398997upload
    -- UT010057: multipart config was not present on Servlet
  - cannot handle max upload size exceeding exception
    -- handleMaxSizeException() in FileUploadExceptionAdvice is executed but not able to redirect to any error page
    
    
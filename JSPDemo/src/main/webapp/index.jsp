<%@ page autoFlush="true" buffer="256kb" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>JSP Demo</title>
        <jsp:useBean id="testBean" class="org.freecode.demo.controller.TestBean" scope="page"></jsp:useBean>
    </head>
    <body>
        <% out.println("Hello, world!"); %>
        <p/>
        App Server Time: <%= testBean.testTime() %> <p/>
        DB System Time: <%= testBean.testDB() %>
    </body>
</html>
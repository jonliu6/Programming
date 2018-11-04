<%@ page autoFlush="true" buffer="256kb" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User Information</title>
    </head>
    <body>
        <%
          String first = (String) request.getAttribute("userFirstName");
          String last = (String) request.getAttribute("userLastName");
        %>
        <span>
          User: <b><%=last%>, <%=first%></b> registered successfully.
        </span>
    </body>
</html>
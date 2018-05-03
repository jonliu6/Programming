<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/staticResources/css/bootstrap.min.css"/>
</head>

<body>
<h1>Please login here:</h1>
<!-- action="login" calls the /login handler mapped in LoginController -->
<!-- Spring Security 4.2.3 seems not having j_spring_security_check -->

<!-- below 2 options both to get the request attribute value, either way -->
     <c:if test="${not empty error}">
         <div class="row">
             <div class="col text-danger">${error}</div>
         </div>
     </c:if>
     <% // similar way using JSP rather than JSTL
         String msg = (String) request.getAttribute("msg");
         if ( msg != null && msg.length() > 0) {
     %>
         <div class="row">
             <div class="col text-info"><%=msg%></div>
         </div>
     <%    	
         }
     %>


<form action="login" name="loginForm" method="POST">
    <label for="username">User ID: </label> <input id="username" type="text" name="username" /><br/>
    <label for="password">Password: </label><input id="password" type="password" name="password" /><br/>
    
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><!-- protection of cross site request forgery. can disable from security XML -->
    <input type="submit" name="submit" value="Login" /> 
</form>

<script type="text/javascript" src="staticResources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/staticResources/js/bootstrap.min.js"></script>
        
</body>
</html>
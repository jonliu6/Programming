<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1" />
    <title>Login Page</title>
</head>

<body>
<h1>Please login here:</h1>
<!-- action="login" calls the /login handler mapped in LoginController -->
<!-- Spring Security 4.2.3 seems not having j_spring_security_check -->
<form action="login" name="loginForm" method="POST">
    <label for="username">User ID: </label> <input id="username" type="text" name="username" /><br/>
    <label for="password">Password: </label><input id="password" type="password" name="password" /><br/>
    
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><!-- protection of cross site request forgery. can disable from security XML -->
    <input type="submit" name="submit" value="Login" /> 
</form>
</body>
</html>
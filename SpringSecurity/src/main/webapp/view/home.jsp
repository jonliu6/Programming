<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
</head>
<body>
  <% out.print("Hello, world!"); %>
  
  <h1>Hi <sec:authentication property="name" /></h1>
  <br/>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
      <a href="#">Link for Admin Only</a>
  </sec:authorize>
  
  <hr/>
  <a href="logout">Logout</a>  
</body>
</html>
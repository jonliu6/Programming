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
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/staticResources/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="staticResources/js/bootstrap.min.js"></script>
        
        <!-- custom JS content -->
        <script type="text/javascript">
            function forceLogout() {
            	$.ajax({
            		url: "logout",
            		method: "GET",
            		crossDomain: true,
            		success: function(resp){
            			console.log("logout successful.");
            		},
            		error: function(jq, st, err){
            			console.log("logout failed: " + err);
            		}
            	});
            }
            
            $(document).ready(function(){
            	$.support.cors = true; // must have to allow local HTML invokes AJAX calls
				$.ajaxSetup({ cache: false }); // disable caches
				
				// window.onbeforeunload = function(e){ // cannot control navigation, refresh scenaios
				// 	forceLogout();
				// };
            });
        </script>
</body>
</html>
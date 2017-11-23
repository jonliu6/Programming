Spring Security
  Features: authentication and authorization for standalone or web-based application
    Authentication: InMemory, Database, LDAP, OpenID
	Authorization: HttpRequest level, Method level, Permissions level
  Security namespace
     <security:authentication-manager>
	   <security:authentication-provider />
	 </security:authentication-manager>
	   
	 <security:http />

This demo is based on a Spring MVC application.
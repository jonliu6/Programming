Spring Security
  Features: authentication and authorization for standalone or web-based application
    Authentication: InMemory, Database, LDAP, OpenID
	Authorization: HttpRequest level, Method level, Permissions level
  Security namespace
     <security:authentication-manager>
	   <security:authentication-provider />
	 </security:authentication-manager>
	   
	 <security:http />

This demo is based on a Spring Security MVC application.
NOTE: at this moment, the version of Spring Security is different from other Spring packages, so you may need to consider the orders for the schemas of descriptors.

This simple application demonstrates a web application using Spring MVC for navigation and Security controls the login and permissions.

The authentication provider uses JDBC connecting to MySQL database (/src/main/database/tables.sql).
The passwords of the users are encoded by MD5 (not recommended for production, which should use BCrypt or SHA256 or SHA512.

App.java provides a way to generate passwords using the encoders of MD5, BCrypt or SHA on the user name.

There are a few descriptors: (can be replaced by Annotations or Java Configuration)
 springSecurityDemo-appConfig.xml: Spring Context Configuration defining beans, autowiring or datasources 
 springSecurityDemo-servletConfig.xml: Spring MVC Configuration defining MVC annotations, autowiring, MVC view resolvers
 springSecurityDemo-securityConfig.xml: Security Configuration defining HTTP security rules, URL patterns, permissions, authentication providers such as In Memory or DB or LDAP and etc.
 web.xml: Dynamic Web Descriptor defining other descriptors, Filters, Dispatcher Servlet and etc.

Also, a property file - db.properties - is used for DB configuration parameters referred in Datasource configuration in Spring Context.

Login.jsp is the entry point, and default URL is home.jsp after the successful login. 
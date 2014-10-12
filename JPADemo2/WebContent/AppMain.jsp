<%@ page
buffer="128kb"
  import="java.util.List,
  java.util.Iterator,
  org.freecode.demo.Employee,
  org.freecode.demo.ViewingEmployees,
  java.util.StringTokenizer"
%>


<jsp:useBean id="theViewingEmployees" class="org.freecode.demo.ViewingEmployees" scope="session"></jsp:useBean>


<html>
<head>
<title>Main Page</title>
</head>
<body>

List:<br/>

<%
  List<Employee> employees = theViewingEmployees.getAllEmployees();

  if ( employees != null && employees.size() >  0 )
  {
%>
<table border="0" width="80%">
<%
	  for(Iterator<Employee> it = employees.iterator(); it.hasNext();)
	  {
		  Employee employee = it.next();
%>
<tr><td><%= employee.employeeID %></td><td><%= employee.startDate %></td></tr>
<%
	  }
%>
</table>
<%
  }
%>

</body>
</html>
<%@ page autoFlush="true" buffer="256kb" 
    import="java.util.Iterator, java.util.List, org.freecode.demo.entity.KnowledgeCategory" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>List Categories</title>
        <jsp:useBean id="cBean" class="org.freecode.demo.entity.KnowledgeCategory" scope="page"></jsp:useBean>
    </head>
    <body>
        <%
          if (request.getAttribute("categories") != null) {
        	  List<KnowledgeCategory> categories = (List<KnowledgeCategory>) request.getAttribute("categories");
        	  if (categories != null) {
        %>
            <b>Categories:</b><p/>
            <ul>
        <%
        		  for (Iterator<KnowledgeCategory> it = categories.iterator(); it.hasNext();) {
        			  KnowledgeCategory kc = it.next();
        			  if (kc != null) {
        %>
                <li><%=kc.getCategoryId()%> - <%=kc.getCategoryName()%></li>
        <%
        			  }
        		  }
        	  }
          }
        %>
            </ul>
    </body>
</html>
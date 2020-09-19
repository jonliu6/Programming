<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Article</title>
</head>
<body>
    <form:form action="publish" modelAttribute="article">
        <b>Title: </b><form:input path="title" /><p/> <!-- path maps to the POJO attribute -->
        <b>Content: </b><form:input path="content" /><p/>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
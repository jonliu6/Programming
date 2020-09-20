<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>New Article</title>
    <style>
        .error {color: red;}
    </style>
</head>
<body>
    <form:form action="publish" modelAttribute="article">
        <b>Title: </b><form:input path="title" /> <form:errors path="title" cssClass="error" /><p/> <!-- path maps to the POJO attribute -->
        <b>Content: </b><form:input path="content" /> <form:errors path="content" cssClass="error" /><p/>
        <input type="submit" value="Submit" />
    </form:form>
</body>
</html>
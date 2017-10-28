<%@ page autoFlush="true" buffer="64kb" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add a book</title>
</head>

<body>
    <form:form method="GET" commandName="newBook" action="view"> <!-- commandName can be replaced by modelAttribute -->
        <label for="txTitle">Title: </label>
        <form:input type="text" id="txTitle" name="txTitle" path="title"/><br/>
        <label for="txIsbn">ISBN: </label>
        <form:input type="text" id="txIsbn" name="txIsbn" path="isbn"/><br/>
        <label for="txPageCount">Page Count: </label>
        <form:input type="text" id="txPageCount" name="txPageCount" path="pageCount"/><br/>
        
        <input type="submit" value="Add" />
    </form:form>
</body>
</html>
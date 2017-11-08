<%@ page autoFlush="true" buffer="64kb" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add a book</title>
    <style>
        .error { color: red; font-size: 15px; }
    </style>
</head>

<body>
    <!-- commandName can be replaced by modelAttribute -->
    <!-- the validation not working when action="view", to check validation, set action="add" --> 
    <form:form method="post" commandName="newBook" action="save" enctype="multipart/form-data">
        <label for="txTitle">Title: </label>
        <form:input type="text" id="txTitle" name="txTitle" path="title"/>
        <br/>
        <label for="txIsbn">ISBN: </label>
        <form:input type="text" id="txIsbn" name="txIsbn" path="isbn"/>
        <form:errors path="isbn" cssClass="error" />
        <br/>
        <label for="txPageCount">Page Count: </label>
        <form:input type="text" id="txPageCount" name="txPageCount" path="pageCount"/>
        <form:errors path="pageCount" cssClass="error" />
        <br/>
        <label for="txPublishedOn">Publish Date: </label>
        <form:input type="text" id="txPublishedOn" name="txPublishedOn" path="publishedOn"/>
        <form:errors path="publishedOn" cssClass="error" />
        <br/>
        <form:input type="file" id="fileAttachment" name="fileAttachment" path="attachment" />
        <input type="submit" value="Add" />
    </form:form>
</body>
</html>
<%@page autoFlush="false" buffer="64kb" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>Demo a book using Spring MVC</title>
</head>

<body>
<h1>Your book is:</h1>
Title: <span>${book.title}</span><br/>
ISBN: <span>${book.isbn}</span><br/>
Pages: <span>${book.pageCount}</span><br/>
</body>
</html>
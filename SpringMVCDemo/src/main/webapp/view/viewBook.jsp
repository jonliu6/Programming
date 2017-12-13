<%@page autoFlush="false" buffer="64kb" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>Hello from Spring MVC</title>
</head>

<body>
<h1>Book Information</h1>
<span>Title: ${bookTitle}</span><br/>
<span>ISBN: ${bookIsbn}</span><br/>
<span>Page Count: ${bookPageCount}</span><br/>
<span>Published: ${bookPublishDate}</span><br/>
<span>Attachment: <a href="download?filename=${bookAttachment}" target="_blank">${bookAttachment}</a></span><br/>
<hr/>
<a href="add">Add Book</a>
</body>
</html>
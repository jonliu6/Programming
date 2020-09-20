<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Single File Upload</title>
</head>
<body>
    <form:form action="uploadSingle" method="POST" enctype="multipart/form-data">
        <label for="fileName">File Name: </label><input type="text" name="fileName" /> <p/>
        <input type="file" name="file" id="fileUpload" />
        <input type="submit" value="Upload Single File" />
    </form:form>
</body>
</html>
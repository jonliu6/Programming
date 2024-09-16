<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Single File Upload</title>
</head>
<body>
    <span>${uploadMessage}</span><p/>
    <form:form action="uploadCommon" method="POST" enctype="multipart/form-data">
        <label for="file">File Name: </label> <input type="file" name="file" id="fileUpload" />
        <input type="submit" value="Upload File" />
    </form:form>
</body>
</html>
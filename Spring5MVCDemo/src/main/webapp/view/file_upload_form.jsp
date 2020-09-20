<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>File Upload</title>
</head>
<body>
    <span>${uploadMessage}</span><p/>
    <form:form action="uploadCommon" method="POST" enctype="multipart/form-data">
        <input type="file" name="file" id="fileUpload" />
        <input type="submit" value="Upload" />
    </form:form>
</body>
</html>
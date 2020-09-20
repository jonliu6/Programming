<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>File Upload</title>
</head>
<body>
    <span>${uploadMessage}</span><p/>
    <span><b>Upload Single File</b></span>
    <!-- uploadSingle maps /uploadSingle in FileUploadController -->
    <!-- for other Controller, need to add full request mappings like article/uploadCommon -->
    <form:form action="uploadSingle" method="POST" enctype="multipart/form-data">
        File Name: <input type="text" name="fileName" />
        <input type="file" name="file" id="fileUpload" />
        <input type="submit" value="Upload Single File" />
    </form:form>
    <p/>
    <span><b>Upload Multiple Files</b></span>
    <form:form action="uploadMultiple" method="POST" enctype="multipart/form-data">
        1st File Name: <input type="text" name="fileName" /> <input type="file" name="file" /><p/>
        2nd File Name: <input type="text" name="fileName" /> <input type="file" name="file" /><p/>
        3rd File Name: <input type="text" name="fileName" /> <input type="file" name="file" /><p/>
        <input type="submit" value="Upload Multiple Files" />
    </form:form>
</body>
</html>
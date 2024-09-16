<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- due to upgrade, taglib http://java.sun.com/jsp/jstl/core replaced to jakarta.tags.core -->
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
        File Name: <input type="text" name="fileName"/>
        <input type="file" name="file" id="fileUpload"/>
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
    <p/>
    <!-- action maps to the request mapping of the method in FileUploadController, and modelAttribute maps to the name of @ModelAttribute in FileUploadController -->
    <form:form action="uploadMultiSelection" method="POST" modelAttribute="attachments" enctype="multipart/form-data" >
        Select Multiple Files: <input type="file" multiple="multiple" name="files" />
        <input type="submit" id="submit" value="Upload Selected Files" />
    </form:form>
    <p/>
    <c:if test="${not empty attachments}" >
    <span><b>Files for download: </b></span><p/>
    <ol>
        <c:forEach items = "${attachments.files}" var="aFile">
            <li><a href="./download?fileName=${aFile.originalFilename}">${aFile.originalFilename}</a></li>
        </c:forEach>
    </ol>
    </c:if>
</body>
</html>
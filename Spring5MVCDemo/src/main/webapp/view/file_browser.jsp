<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Uploaded Files</title>
</head>
<body>
    <b>Current Folder: ${currentFolder}</b><p/>
    <span>File List:</span><br/>
    <ul>
        <c:forEach items="${fileList}" var="fileName">
            <li>
                <c:choose>
                    <c:when test="${ fn:startsWith(fileName, '[') or fn:startsWith(fileName, '..')}">${fileName}</c:when>
                    <c:otherwise><a href="./download?fileName=${fileName}">${fileName}</a></c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
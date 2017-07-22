# July 2017
# This is a Maven project - a demo of file upload and download thru:
# 1. Servlet 3.0 (index.html)
# 2. or JSF 2.x using Part from ManagedBean (jsfdemo.xhtml)

# Test Environment
# Web Server: Wildfly 8.1
# Build/Package Tool: Maven 3.3
# URL: http://localhost:8080/FileUploadDemo or http://localhost:8080/FileUploadDemo/jsfdemo.faces

# Files
# UploadBean.java: JSF ManagedBean supporting jsfdemo.xhtml
# FileSizeValidator.java: JSF custom validator used by jsfdemo.xhtml
# faces-config.xml: JSF configuration
# FileUploadHelper.java: class handling the common IO operations
# FileUploadServlet.java: Servlet implementation for file upload - used by index.html
# FileDownloadServlet.java: Servlet implementation for file download - used by jsfdemo.xhtml
# messages.properties: application properties file
# web.xml: Web App Deploy Descriptor

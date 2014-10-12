<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE struts-config PUBLIC 
"-//Apache Software Foundation//DTD Struts Configuration 1.3//EN" 
"http://jakarta.apache.org/struts/dtds/struts-config_1_3.dtd">

<%@ page import="java.util.List, java.util.ArrayList, java.util.Iterator, org.freecode.demo.model.Question" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-logic" prefix="logic" %>
<%@ taglib uri="struts-nested" prefix="nested" %>
<html>
<head>
<title>Show Questions</title>
</head>

<body>
<%
  List questions = (ArrayList) request.getAttribute("QUESTION_LIST");
  
%>

<logic:iterate name="QUESTION_LIST" id="aQuestion">
  <bean:write name="aQuestion" property="questionId"/>&nbsp;<bean:write name="aQuestion" property="questionText"/><br/>
  
  <logic:equal name="aQuestion" property="questionType" value="TEXT">
  <html:text name="aQuestion" property="questionId"></html:text>
  </logic:equal>
  <logic:notEqual name="aQuestion" property="questionType" value="TEXT">
  <html:select name="aQuestion" property="questionId">
    <html:option value="1">A</html:option>
    <html:option value="2">B</html:option>
    <html:option value="3">C</html:option>
  </html:select>
  </logic:notEqual>
  <p/>
</logic:iterate>

<!-- <input type="hidden" name="questionCount" value="<%=questions.size()%>"/> //-->
<html:hidden name="QUESTION_FORM" property="questionCount" />
</body>
</html>
<%-- 
    Document   : applicant
    Created on : Aug 7, 2015, 10:34:58 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Applicant</title>
        <link rel="stylesheet" href="css/style2.css" type="text/css">
    </head>
    <body>
        <s:if test="#session.loggedinApp != 'true'">
            <jsp:forward page="login.jsp" />
        </s:if>
        <h1>Hello <s:property value="username"/></h1>
        <s:form action="LoadVacancy" method="POST">
            <s:select list="departments" label="Choose department" headerKey="-1" headerValue="Choose department" name="department"></s:select>
            <s:submit value="Next"/>
        </s:form>
    </body>
</html>

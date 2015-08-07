<%-- 
    Document   : manager
    Created on : Jul 23, 2015, 8:57:41 AM
    Author     : tuananh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration</title>
        <link href="../template/css/style-admin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="header">
            <div class="clearfix">
                <div class="logo">
                    <img src="../template/images/logo.png" alt=""/>
                </div>
            </div>
        </div>
        <div id="contents">
            <div class="clearfix">
                <div id="left">
                    <s:if test="#session.loggedin != 'true'">
                        <jsp:forward page="index.jsp" />
                    </s:if>
                    <%--<s:action name="SelectVacancy" executeResult="true"/>--%>
                    <s:set var="username" value="#session.username" />
                    <%--<s:set var="titles" value="#attr.titles"/>--%>
                    <h1>Hello <s:property value="username"/></h1>
                    <ul class="navigation2">
                        <li><s:a href="LoadInterview.action">Create Interview</s:a></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div id="right">
                </div>
            </div>
        </div>



        <div id="footnote">
            <div class="clearfix"></div>
        </div>
    </body>
</html>

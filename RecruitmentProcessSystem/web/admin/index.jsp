<%-- 
    Document   : login
    Created on : Jul 23, 2015, 8:58:33 AM
    Author     : tuananh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
        <s:if test="#session.loggedin == 'true'">
            <jsp:forward page="manager.jsp" />
        </s:if>
        <div style="margin-top: 40px;padding-left: 500px">
            <h1>Admin Login</h1>
            <s:form method="POST" action="LoginAdmin">
                <s:textfield name="username" label="Username" /> <br/>
                <s:password name="password" label="Password"/>
                <s:submit value="Login" />
            </s:form>
        </div>
    </body>
</html>

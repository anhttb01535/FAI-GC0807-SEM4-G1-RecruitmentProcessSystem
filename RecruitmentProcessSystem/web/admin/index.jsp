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
    </head>
    <body>
        <h1>Login</h1>
        <s:form method="POST" action="LoginAdmin">
            Username: <s:textfield name="username" /> <br/>
            Password: <s:password name="password" />
            <s:submit value="Login" />
        </s:form>
    </body>
</html>

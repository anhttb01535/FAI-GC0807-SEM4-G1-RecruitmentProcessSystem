<%-- 
    Document   : loadinterview
    Created on : Aug 7, 2015, 6:31:48 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //response.sendRedirect("ReloadInterviewApp");
        %>
        <s:action name="ReloadInterviewApp" executeResult="true"/>
    </body>
</html>

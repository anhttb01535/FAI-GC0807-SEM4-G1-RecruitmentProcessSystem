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
        <link media="screen" rel="stylesheet" type="text/css" href="css/admin.css"  />
        <title>Administration</title>
    </head>
    <body>
        <s:if test="#session.loggedin != 'true'">
            <jsp:forward page="index.jsp" />
        </s:if>
        <!--[if !IE]>start wrapper<![endif]-->
        <div id="wrapper">
            <s:include value="header.jsp" />  
            <!--[if !IE]>start content<![endif]-->
            <div id="content">
                <!--[if !IE]>start page<![endif]-->
                <div id="page">
                    <div class="inner">
                        <!--[if !IE]>start section<![endif]-->	

                        <!--[if !IE]>end section<![endif]-->

                    </div>
                </div>
                <!--[if !IE]>end page<![endif]-->
                <!--[if !IE]>start sidebar<![endif]-->
                <!--[if !IE]>end sidebar<![endif]-->
            </div>
            <!--[if !IE]>end content<![endif]-->
        </div>
    </body>
</html>

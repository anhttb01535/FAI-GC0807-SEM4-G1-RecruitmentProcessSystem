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
        <link media="screen" rel="stylesheet" type="text/css" href="css/admin-login.css"  />
        <title>Login</title>
    </head>
    <body>
        <s:if test="#session.loggedin == 'true'">
            <jsp:forward page="manager.jsp" />
        </s:if>
        <div id="wrapper">
            <!--[if !IE]>start login wrapper<![endif]-->
            <div id="login_wrapper">
                <!--[if !IE]>start login<![endif]-->
                <form action="LoginAdmin" method="post">
                    <fieldset>
                        <div class="error">
                            <div class="error_inner">
                                <strong><s:property value="error"/></strong>
                            </div>
                        </div>
                        <div class="formular">
                            <div class="formular_inner">
                                <label>
                                    <strong>User Name: </strong> 
                                    <span class="input_wrapper">
                                        <s:textfield name="username"/>
                                    </span>
                                </label>
                                <label>
                                    <strong>Password:</strong>
                                    <span class="input_wrapper">
                                        <s:password name="password"/>
                                    </span>
                                </label>
                                <!--                                <label class="inline">
                                                                    <input class="checkbox" name="" type="checkbox" value="" />
                                                                    remember me on this computer
                                                                </label>-->
                                <ul class="form_menu">
                                    <li><span class="button"><span><span><s:submit value="Login" align="center">Login</s:submit></span></span></span></li>
                                </ul>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <!--[if !IE]>end login<![endif]-->
            </div>
            <!--[if !IE]>end login wrapper<![endif]-->
        </div>
    </body>
</html>

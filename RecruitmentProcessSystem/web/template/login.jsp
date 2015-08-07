<%-- 
    Document   : login
    Created on : Jul 23, 2015, 8:53:23 AM
    Author     : tuananh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recruitment Process System</title>
        <link rel="stylesheet" href="css/style2.css" type="text/css">
    </head>
    <body>
        <div id="header">
            <div class="clearfix">
                <div class="logo">
                    <a href="index.jsp"><img src="images/logo.png" alt="LOGO" height="52" width="362"></a>
                </div>
                <ul class="navigation">
                    <li class="active">
                        <a href="index.jsp">Home</a>
                    </li>
                    <li>
                        <a href="about.jsp">About Us</a>
                    </li>				
                    <li>
                        <a href="login.jsp">Login</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="contents">
            <div style="margin-top: 40px;padding-left: 500px">
                <s:if test="#session.loggedin == 'true'">
                    <jsp:forward page=".jsp" />
                </s:if> 

                <h1 >Login to System</h1>
                <div style="border: double 1px black; width: 260px;padding: 5px 10px 10px 10px">
                    <s:form method="POST" action="LoginApplicant" >
                        <s:textfield name="username" label="Username"/> <br/>
                        <s:password name="password" label="Password" />
                        <s:submit value="Login" />
                    </s:form>
                </div>
            </div>
        </div>
        <div id="footer">
            <div class="clearfix">
                <div class="section">
                    <h4>....</h4>

                </div>
                <div class="section contact">
                    <h4>......</h4>
                </div>
                <div class="section">
                    <h4>Contact Us</h4>
                    <p>
                        <span>Address:</span> FPT,8 Ton That Thuyet
                    </p>
                    <p>
                        <span>Phone:</span> (+84) 964402794
                    </p>
                    <p>
                        <span>Email:</span> cuongpm119@gmail.com
                    </p>	
                </div>
            </div>
            <div id="footnote">
                <div class="clearfix">

                    <p>
                        © Copyright 2023 Manes Winchester. All Rights Reserved.
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>

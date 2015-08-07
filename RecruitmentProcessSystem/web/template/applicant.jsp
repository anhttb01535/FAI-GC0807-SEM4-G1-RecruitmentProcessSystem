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
                    
                </ul>
            </div>
        </div>
        
        <s:if test="#session.loggedinApp != 'true'">
            <jsp:forward page="login.jsp" />
        </s:if>
        <div style="margin-top: 40px;padding-left: 500px;min-height: 300px">
            <h1>Hello <s:property value="username"/></h1>
            <s:form action="LoadVacancy" method="POST">
                <s:select list="departments" label="Choose department" headerKey="-1" headerValue="Choose department" name="department"></s:select>
                <s:submit value="Next"/>
            </s:form>
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
                        © Copyright 2015
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>

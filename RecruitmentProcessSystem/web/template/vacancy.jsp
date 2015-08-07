<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacancy | RecruitmentProceesSystem</title>
        <link rel="stylesheet" href="css/style2.css" type="text/css">
    </head>
    <body>
<<<<<<< HEAD
        <h1>Department: <s:property value="department"/></h1>
        <s:iterator value="vacancies" var="v">
            <s:div>
                <s:form action="LoadInterviewApp" method="POST">
                    <s:set value="#v.id" name="id1"/>
                    <h3><s:property value="#id1" /></h3>
                    <s:textfield readonly="true" name="id" value="%{id1}"/>
                    <h3><s:property value="#v.title"/></h3>
                    <h3><s:property value="#v.numberOfApplicant"/></h3>
                    <h3><s:property value="#v.status"/></h3>                   
                    <s:submit value="Interview schedule"/>
                </s:form>
            </s:div>
        </s:iterator>
=======
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
        <div style="margin-top: 40px;padding-left: 200px;min-height: 300px">
            <h1>Department: <s:property value="department"/></h1>
            <s:iterator value="vacancies" var="v">
                <s:div>
                    <s:form action="LoadInterviewApp" method="POST">
                        <s:set value="#v.id" name="id1"/>
                        <table>
                            <tr>
                                <td>ID</td>
                                <td>Title</td>
                                <td>Number Of Applicant</td>
                                <td>Status</td>
                                <td>Submit</td>
                            </tr>
                            <tr>
                                <td><s:property value="#id1" /></td>
                                <td><s:property value="#v.title"/></td>
                                <td><s:property value="#v.numberOfApplicant"/></td>
                                <td><s:property value="#v.status"/></td>
                                <td><s:submit value="Interview schedule"/></td>
                            </tr>

                        </table>


                    </s:form>
                </s:div>
            </s:iterator>
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
>>>>>>> origin/master
    </body>
</html>

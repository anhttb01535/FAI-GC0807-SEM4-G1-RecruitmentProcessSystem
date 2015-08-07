<%-- 
    Document   : createinterview
    Created on : Aug 6, 2015, 8:40:58 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-2.1.4.min.js" />
        <script type="text/javascript">
            /*$('#selectdepartment').change(function () {
             
             if ($(this).val() === '') {
             l
             }
             
             });*/
            function handleChange(value) {
                var element = document.getElementById("selectVacancy");
                var element1 = document.getElementById("selectInterviewer");
                element.setAttribute("list", vacancies.innerHTML);
                element1.setAttribute("list", interviewers.innerHTML);
            }
        </script>
        <title>Create Interview | RecruitmentProcessSystem</title>
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
                    <div id="right" >
                    <s:form action="CreateInterview" method="POST">
                        <s:select id="selectdepartment" label="Choose department" list="departments" listValue="name" headerKey="-1" headerValue="Select department" name="department" onchange="handleChange(this.value)"></s:select>
                        <s:textfield name="startDate" label="Start date"/>
                        <s:textfield name="endDate" label="End date"/>                     
                        <s:select id="selectVacancy" label="Choose vacancy" list="vacancies" listValue="title" headerKey="-1" headerValue="Select vacancy" name="vacancy"></s:select>
                        <s:select id="selectInterviewer" label="Choose interviewer" list="interviewers" listValue="name" headerKey="-1" headerValue="Select interviewer" name="interviewer"></s:select>
                        <s:submit name="submit" value="Create"/>
                    </s:form>
                </div>
            </div>
        </div>


        <div id="footnote">
            <div class="clearfix"></div>
        </div>
    </body>
</html>

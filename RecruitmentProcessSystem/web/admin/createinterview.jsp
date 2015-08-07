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
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="CreateInterview" method="POST">
            <s:select id="selectdepartment" label="Choose department" list="departments" listValue="name" headerKey="-1" headerValue="Select department" name="department"></s:select>
            <s:textfield name="startDate" label="Start date"/>
            <s:textfield name="endDate" label="End date"/>                     
            <s:select id="selectVacancy" label="Choose vacancy" list="vacancies" listValue="title" headerKey="-1" headerValue="Select vacancy" name="vacancy"></s:select>
            <s:select id="selectInterviewer" label="Choose interviewer" list="interviewers" listValue="name" headerKey="-1" headerValue="Select interviewer" name="interviewer"></s:select>
            <s:submit name="submit" value="Create"/>
        </s:form>
    </body>
</html>

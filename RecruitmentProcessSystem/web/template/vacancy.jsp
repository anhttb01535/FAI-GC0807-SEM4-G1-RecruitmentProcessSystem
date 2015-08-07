<%-- 
    Document   : vacancy
    Created on : Aug 7, 2015, 10:56:04 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vacancy</title>
    </head>
    <body>
        <h1>Department: <s:property value="department"/></h1>
        <s:iterator value="vacancies" var="v">
            <s:div>
                <s:form action="LoadInterviewApp" method="POST">
                    <s:set value="#v.id" name="id1"/>
                    <h3><s:property value="#id1" /></h3>
                    <h3><s:property value="#v.title"/></h3>
                    <h3><s:property value="#v.numberOfApplicant"/></h3>
                    <h3><s:property value="#v.status"/></h3>
                    <s:param name="id"><s:property value="#id1"/></s:param>
                    <s:submit value="Interview schedule"/>
                </s:form>
            </s:div>
        </s:iterator>
    </body>
</html>

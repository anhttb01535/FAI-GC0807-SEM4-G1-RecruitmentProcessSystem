<%-- 
    Document   : interview
    Created on : Aug 7, 2015, 11:24:06 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interview</title>
    </head>
    <body>
        <h1>VacancyID: <s:property value="id"/></h1>
        <s:set value="#{0}" var="d"/>
        <s:iterator value="interviews" var="i">
            <s:div>
                <s:form action="ApplyInterview" method="POST">
                    <s:set value="#i.id" name="id"/>
                    <h3><s:property value="#i.dateOfStartInterview"/></h3>
                    <h3><s:property value="#i.dateOfEndInterview"/></h3>
                    <h3><s:property value="#i.idVacancy.id"/></h3>
                    <s:if test="#{status.get(d).equals('yes')}">
                        <s:submit value="Apply"/>
                    </s:if>
                </s:form>
            </s:div>
        </s:iterator>
    </body>
</html>

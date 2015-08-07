<%-- 
    Document   : interview
    Created on : Aug 7, 2015, 11:24:06 AM
    Author     : trant
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <s:set var="d">0</s:set>
        <s:set value="status" var="#st"/>
        <s:iterator value="interviews" var="i">
            <s:div>
                <s:form action="ApplyInterview" method="POST">
                    <s:set value="#i.id" name="id"/>
                    <s:set value="#i.id" name="id1"/>
                    <h3><s:property value="#d"/></h3>
                    <h3><s:property value="#id1"/></h3>
                    <s:textfield readonly="true" name="id" value="%{id1}"/>
                    <h3><s:property value="#i.dateOfStartInterview"/></h3>
                    <h3><s:property value="#i.dateOfEndInterview"/></h3>
                    <h3><s:property value="#i.idVacancy.id"/></h3>
                    <c:choose>
                        <c:when test="${st.get(d).equals('no')}">
                            <s:submit value="Apply"/>
                        </c:when>
                        <c:otherwise>
                            <s:submit value="Apply" disabled="true"/>
                        </c:otherwise>
                    </c:choose>
                    <s:set var="d">${d+1}</s:set>
                </s:form>
            </s:div>
        </s:iterator>
    </body>
</html>

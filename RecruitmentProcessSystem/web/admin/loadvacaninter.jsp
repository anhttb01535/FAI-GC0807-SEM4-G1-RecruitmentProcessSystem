<%-- 
    Document   : addinterview
    Created on : Aug 7, 2015, 10:31:35 PM
    Author     : trant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link media="screen" rel="stylesheet" type="text/css" href="css/admin.css" />
        <script type="text/javascript" src="js/behaviour.js"></script>
        <title>Add interview</title>
    </head>
    <body>
        <s:if test="#session.loggedin != 'true'">
            <jsp:forward page="index.jsp" />
        </s:if>
        <!--[if !IE]>start wrapper<![endif]-->
        <s:div id="wrapper">
            <s:include value="header.jsp" />  
            <!--[if !IE]>start content<![endif]-->
            <s:div id="content">
                <!--[if !IE]>start page<![endif]-->
                <s:div id="page">
                    <s:div cssClass="inner">
                        <!--[if !IE]>start section<![endif]-->	
                        <s:div cssClass="section table_section">
                            <!--[if !IE]>start title wrapper<![endif]-->
                            <s:div cssClass="title_wrapper">
                                <h2>Add new interview</h2>
                                <span class="title_wrapper_left"></span>
                                <span class="title_wrapper_right"></span>
                            </s:div>
                            <!--[if !IE]>end title wrapper<![endif]-->
                            <!--[if !IE]>start section content<![endif]-->
                            <s:div cssClass="section_content">
                                <!--[if !IE]>start section content top<![endif]-->
                                <s:div cssClass="sct">
                                    <s:div cssClass="sct_left">
                                        <s:div cssClass="sct_right">
                                            <s:div cssClass="sct_left">
                                                <s:div cssClass="sct_center">
                                                    <s:form action="LoadVacanInter">
                                                        <table align="center" cellpadding="5" cellspacing="1">
                                                            <tr>
                                                                <td align="left" colspan="2">
                                                                    <s:select list="departments" label="Choose department" headerKey="-1" headerValue="Choose department" name="department"></s:select>
                                                                    <s:submit value="NEXT"/>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </s:form>
                                                </s:div>
                                            </s:div>
                                        </s:div>
                                    </s:div>
                                    <!--[if !IE]>end section content top<![endif]-->
                                    <!--[if !IE]>start section content bottom<![endif]-->
                                    <span class="scb"><span class="scb_left"></span><span class="scb_right"></span></span>
                                    <!--[if !IE]>end section content bottom<![endif]-->
                                </s:div>
                                <!--[if !IE]>end section content<![endif]-->
                            </s:div>
                            <!--[if !IE]>end section<![endif]-->
                        </s:div>
                    </s:div>
                </s:div>
                <!--[if !IE]>end page<![endif]-->
            </s:div>
            <!--[if !IE]>end content<![endif]-->
        </s:div>
    </body>
</html>

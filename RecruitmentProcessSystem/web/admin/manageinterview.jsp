<%-- 
    Document   : manageinterview
    Created on : Aug 7, 2015, 9:11:20 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>JSP Page</title>
        <link media="screen" rel="stylesheet" type="text/css" href="css/admin.css"  />
        <script type="text/javascript" src="js/behaviour.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <s:include value="header.jsp" />  
            <!--[if !IE]>start content<![endif]-->
            <div id="content">
                <!--[if !IE]>start page<![endif]-->
                <div id="page">
                    <div class="inner">
                        <!--[if !IE]>start section<![endif]-->	
                        <div class="section table_section">
                            <!--[if !IE]>start title wrapper<![endif]-->
                            <div class="title_wrapper">
                                <h2>Interview Information</h2>
                                <span class="title_wrapper_left"></span>
                                <span class="title_wrapper_right"></span>
                            </div>
                            <!--[if !IE]>end title wrapper<![endif]-->
                            <!--[if !IE]>start section content<![endif]-->
                            <div class="section_content">
                                <!--[if !IE]>start section content top<![endif]-->
                                <div class="sct">
                                    <div class="sct_left">
                                        <div class="sct_right">
                                            <div class="sct_left">
                                                <div class="sct_right">
                                                    <form action="#">
                                                        <fieldset>
                                                            <!--[if !IE]>start table_wrapper<![endif]-->
                                                            <div class="table_wrapper">
                                                                <div class="table_wrapper_inner">
                                                                    <table cellpadding="0" cellspacing="0" width="100%">
                                                                        <tbody><tr>
                                                                                <th>Interview Id</th>
                                                                                <th>Date of start</th>
                                                                                <th>Date of end</th>
                                                                                <th>Vacancy</th>
                                                                                <th>Interviewer</th>
                                                                                <th style="width: 96px;">Actions</th>
                                                                            </tr>
                                                                            <s:iterator value="interviews">
                                                                                <tr class="first">
                                                                                    <td><s:property value="id"/></td>
                                                                                    <td><s:property value="dateOfStartInterview"/></td>
                                                                                    <td><s:property value="dateOfEndInterview"/></td>
                                                                                    <td><s:property value="idVacancy.title"/></td>
                                                                                    <td><s:property value="idInterviewer.name"/></td>
                                                                                    <td>
                                                                                        <div class="actions">
                                                                                            <ul>
                                                                                                <li>
                                                                                                    <s:a action="UpdateInterview" cssClass="action3">
                                                                                                        <s:param name="interviewId"><s:property value="id"></s:property></s:param>
                                                                                                    </s:a>
                                                                                                </li>
                                                                                                <li>
                                                                                                    <s:a action="DeleteInterview" cssClass="action4" onclick="return confirm('Do you want to delete ?');">
                                                                                                        <s:param name="interviewId"><s:property value="id"></s:property></s:param>
                                                                                                    </s:a>
                                                                                                </li>
                                                                                            </ul>
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </s:iterator>
                                                                        </tbody></table>
                                                                </div>
                                                            </div>
                                                            <!--[if !IE]>end table_wrapper<![endif]-->
                                                            <!--[if !IE]>start table menu<![endif]-->
                                                            <div class="table_menu">
                                                                <ul class="left">
                                                                    <li><s:a href="LoadDepartment.action" class="button add_new"><span><span>ADD NEW</span></span></a></li>
                                                                </ul>
                                                            </div>
                                                            <!--[if !IE]>end table menu<![endif]-->
                                                        </fieldset>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--[if !IE]>end section content top<![endif]-->
                                <!--[if !IE]>start section content bottom<![endif]-->
                                <span class="scb"><span class="scb_left"></span><span class="scb_right"></span></span>
                                <!--[if !IE]>end section content bottom<![endif]-->
                            </div>
                            <!--[if !IE]>end section content<![endif]-->
                        </div>
                        <!--[if !IE]>end section<![endif]-->
                        <!--[if !IE]>start section<![endif]-->	
                        <div class="section">
                            <!--[if !IE]>start title wrapper<![endif]-->
                            <!--[if !IE]>end title wrapper<![endif]-->
                            <!--[if !IE]>start section content<![endif]-->
                            <!--[if !IE]>end section content<![endif]-->
                        </div>
                        <!--[if !IE]>end section<![endif]-->
                    </div>
                </div>
                <!--[if !IE]>end page<![endif]-->
                <!--[if !IE]>start sidebar<![endif]-->
            </div>
            <!--[if !IE]>end sidebar<![endif]-->
        </div>
    </body>
</html>

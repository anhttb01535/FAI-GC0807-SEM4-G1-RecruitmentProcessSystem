<%-- 
    Document   : header
    Created on : Jan 1, 2013, 9:14:22 PM
    Author     : DinhThanh
--%>
<%@page import="java.util.Map"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script src="../ckeditor/ckeditor.js"></script>
<!--[if !IE]>start head<![endif]-->
<div id="head">
    <!--[if !IE]>start logo and user details<![endif]-->
    <div id="logo_user_details">
        <h1 id="logo"><a href="#">websitename Administration Panel</a></h1>
        <!--[if !IE]>start user details<![endif]-->
        <div id="user_details">
            <ul id="user_details_menu">
                <li>Welcome <strong><s:property value="#session.username"/></strong></li>
                <li>
                    <ul id="user_access">
                        <li class="first"><a href="#">My account</a></li>
                        <li class="last"><a href="LogoutAdmin.action">Log out</a></li>
                    </ul>
                </li>
                <!--<li><a class="new_messages" href="#">4 new messages</a></li>-->
            </ul>
            <!--[if !IE]>start search<![endif]-->
            <!--<div id="search_wrapper">
                <form action="#">
                    <fieldset>
                        <label>
                            <input class="text" name="" type="text" />
                        </label>
                        <span class="go"><input name="" type="submit" /></span>
                    </fieldset>
                </form>-->
            <!--                <ul id="search_wrapper_menu">
                                <li class="first"><a href="#">Advanced Search</a></li>
                                <li class="last"><a href="#">Admin Map</a></li>
                            </ul>-->
        </div>
        <!--[if !IE]>end search<![endif]-->
    </div>
    <!--[if !IE]>end user details<![endif]-->
    <!--[if !IE]>end logo end user details<![endif]-->
    <!--[if !IE]>start menus_wrapper<![endif]-->
    <div id="menus_wrapper">
        <div id="main_menu">
            <ul>
                <li><a href="/RecruitmentProcessSystem/admin/manager.jsp"><span><span>Main</span></span></a></li>
                <li><a href="GetAllInterview.action"><span><span>Interview</span></span></a></li>
                <li><s:a action="GetAllProduct"><span><span>Interviewer</span></span></s:a></li>
                    <li><a href="GetAllUser.action"><span><span>Applicant</span></span></a></li>
                    <li><s:a action="GetAllBill"><span><span>Vacancy</span></span></s:a></li>
                <li class="last"><a href="GetAllFeedBackForAdmin.action"><span><span>Department</span></span></a></li>
            </ul>
        </div>
        <!--        <div id="sec_menu">
                    <ul>
                        <li><a href="#" class="sm1">Security Settings</a></li>
                        <li><a href="#" class="sm2">Chat and PMs</a></li>
                        <li><a href="#" class="sm3">Search Options</a></li>
                        <li><a href="#" class="sm4">Moderators</a></li>
                        <li><a href="#" class="sm5">Upload Options</a></li>
                        <li><a href="#" class="sm6">Download Options</a></li>
                        <li><a href="#" class="sm7">Emails</a></li>
                        <li>
                            <span class="drop"><span><span><a href="#" class="sm8">More</a></span></span></span>
                            <ul>
                                <li><a class="sm6" href="#">Download options</a></li>
                                <li><a class="sm4" href="#">Menu item</a></li>
                                <li><a class="sm6" href="#">Download options</a></li>
                                <li><a class="sm6" href="#">Download options</a></li>
                                <li><a class="sm6" href="#">Download options</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>-->
    </div>
    <!--[if !IE]>end menus_wrapper<![endif]-->
</div>
<!--[if !IE]>end head<![endif]-->
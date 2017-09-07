<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/10/2017
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--@elvariable id="signupData" type="ir.sk.jcg.web.logic.data.SignupData"--%>
<dl class="tabs" data-tab style="background-color: #9c9c9c">
    <dd <c:if test="${!signupData.hasError and !signupData.succeed}"> class="active" </c:if> >
        <a href="#login">
            <i class="icon-login"></i> ورود
        </a>
        <i class="arrow"></i>
    </dd>
    <dd <c:if test="${signupData.hasError or signupData.succeed}"> class="active" </c:if>>
        <a href="#signup">
            <i class="icon-user_add"></i> ثبت نام
        </a>
        <i class="arrow"></i>
    </dd>
    <a id="s-header-tabs-close">
        <i class="icon-close"></i>
    </a>
</dl>
<div class="tabs-content" style="background-color: #5F5F5F">
    <div class="content <c:if test="${!signupData.hasError and !signupData.succeed}"> active </c:if>"
         id="login">
        <jsp:include page="../login.jsp"/>
    </div>
    <div class="content <c:if test="${signupData.hasError or signupData.succeed}"> active </c:if>" id="signup">
        <jsp:include page="../signup.jsp"/>
    </div>
</div>

<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/6/2017
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--@elvariable id="signupData" type="ir.sk.jcg.web.logic.data.SignupData"--%>
<%--@elvariable id="loginData" type="ir.sk.jcg.web.logic.data.LoginData"--%>
<%--@elvariable id="basket" type="ir.sk.jcg.web.logic.data.BasketSessionData"--%>
<%--<div class="row">
    <a href="<s:url value="/"/>">
        <img src="<s:url value="/resources/images/favicon.ico"  />" id="s-logo">
    </a>


    <div class="left" id="s-header-nav">
        &lt;%&ndash; todo &ndash;%&gt;
        <ul style="margin-bottom: .9rem">
            <sec:authorize access="isAnonymous()">
                <li><a href="<s:url value="/login" />" id="s-login-top"><span style="vertical-align: super">ورود</span><i class="material-icons md-dark" style="color: #2ba6cb">input</i></a></li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated() and hasAuthority('SUPER_ADMIN')">
                <li><a href="<s:url value="/manage/users"/>">کاربران<i class="icon icon-users"></i></a></li>
                <li><a href="<s:url value="/manage/production/new"/>">محصول جدید<i class="icon icon-plus"></i></a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.user" var="user"/>
                <li>
                    <span style="vertical-align: super">${user.profile.fullName} &nbsp;</span>
                    <a href="<s:url value="/logout/p" />"><span style="vertical-align: super">(خروج)</span><i class="material-icons md-dark" style="color: #2ba6cb">power_settings_new</i></a>
                </li>
            </sec:authorize>
            &lt;%&ndash;<c:if test="${not empty basket.items}">
                <c:set var="basketColorClass">
                    green-basket
                </c:set>
                <c:set var="basketCount">
                    ${fn:length(basket.items)}
                </c:set>
            </c:if>&ndash;%&gt;
            &lt;%&ndash;<li>
                <a href="#" id="s-basket-top" class="basket-icon-text">
                    سبد خرید
                    <c:if test="${not empty basketCount}">:
                        <div class="count-on-basket">${basketCount}</div>
                        <img src="<s:url value="/r/img/basket-count.png"/>" class="count-on-basket-bg">
                    </c:if>
                    <i class="icon icon-basket ${basketColorClass}"></i>
                    ${basketCount}
                </a>
            </li>&ndash;%&gt;
        </ul>
    <sec:authorize access="isAuthenticated() and hasAuthority('SUPER_ADMIN')">
        <ul style="margin-bottom: .9rem">
            <li><a class="" href="<s:url value="/manage/users"/>"><span style="vertical-align: super">نامه ها</span><i class="material-icons md-dark" style="color: #2ba6cb">inbox</i></a></li>
            <li><a class="" href="<s:url value="/manage/users"/>"><span style="vertical-align: super">اعلان ها</span><i class="material-icons md-dark" style="color: #2ba6cb">add_alert</i></a></li>
            <li><a class="" href="<s:url value="/manage/users"/>"><span style="vertical-align: super">تنظیمات</span><i class="material-icons md-dark" style="color: #2ba6cb">settings</i></a></li>
        </ul>
    </sec:authorize>
    </div>
    <sec:authorize access="isAnonymous()">
        <div id="s-header-tabs"
                <c:if test="${empty signupData.redirect and (signupData.hasError or signupData.succeed or loginData.hasError)}"> style="display: block;" </c:if>
        >
            <jsp:include page="header_guest.jsp"/>
        </div>
    </sec:authorize>
</div>--%>

<script type="text/javascript">
    $('#s-login-top').on("click.main", function (e) {
        e.preventDefault();
        var p = $('#s-header-tabs');
        if (p.is(':visible'))
            p.hide("fade", 100);
        else
            p.show("fade", 100);
        return true;
    });
    $('#s-header-tabs-close').on('click.main', function (e) {
        e.preventDefault();
        $('#s-header-tabs').hide("fade", 100);
        return true;
    });
</script>


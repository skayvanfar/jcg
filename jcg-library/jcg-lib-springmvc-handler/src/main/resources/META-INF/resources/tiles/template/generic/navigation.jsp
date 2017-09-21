<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/10/2017
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<div class="row navigation">
    <nav>
        <ul>
            <li class="left search">
                <i class="icon-magnifying_glass"></i>
                <ul class="pull-left">
                    <form method="get" action="<s:url value="/production/search" />">
                        <input type="search" name="query" placeholder="جستجو کنید ...">
                    </form>
                </ul>
            </li>
            <%--@elvariable id="categories" type="java.util.List<ir.jcg.test.access.entities.Category>"--%>
            <c:forEach items="${menus}" var="menu" varStatus="ci">
                <li>
                    <c:choose>
                        <c:when test="${not empty fn:trim(menu.url)}">
                            <a href="<s:url value="${menu.url}"/>">${menu.title}</a>
                        </c:when>
                        <c:otherwise>
                            ${menu.title}
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${not empty menu.children}">
                        <ul>
                            <c:forEach items="${menu.children}" var="subMenu" varStatus="chi">
                                <li>
                                    <c:choose>
                                        <c:when test="${not empty fn:trim(subMenu.url)}">
                                            <a href="<s:url value="${subMenu.url}"/>">${subMenu.title}</a>
                                        </c:when>
                                        <c:otherwise>
                                            ${subMenu.title}
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

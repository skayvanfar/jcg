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
            <c:forEach items="${categories}" var="c" varStatus="ci">
                <li>
                    <a href="<s:url value="/production/search?query=&cat=${c.title}"/>">${c.title}</a>
                    <c:if test="${not empty c.children}">
                        <ul>
                            <c:forEach items="${c.children}" var="ch" varStatus="chi">
                                <li>
                                    <a href="<s:url value="/production/search?query=&cat=${ch.title}"/>">${ch.title}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>

<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ attribute name="modelAttribute" required="false" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="list" type="ir.sk.patienttalk.common.persistence.jpa.PagingDataList" required="false" rtexprvalue="true"
              deferredValue="false" %>
<%@ attribute name="pageCount" required="false" type="java.lang.Integer" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="currentPage" required="false" type="java.lang.Integer" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="action" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="name" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="method" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>


<c:if test="${empty method}">
    <c:set var="method" value="post"/>
</c:if>

<c:if test="${empty name}">
    <c:set var="name" value="page"/>
</c:if>
<c:if test="${not empty list}">
    <c:set var="pageCount" value="${list.pageCount}"/>
    <c:set var="currentPage" value="${list.page}"/>
</c:if>


<c:if test="${pageCount > 1}">
    <s:url value="${action}" var="action"/>
    <sf:form method="${method}" modelAttribute="${modelAttribute}" id="${id}" action="${action}">
        <jsp:doBody/>
        <ul class="a-pagination ${cssClass}">
            <c:if test="${pageCount>9}">
                <li class="arrow unavailable">
                    <button name="${name}" value="0" type="submit" class="btn btn-info">&laquo;</button>
                </li>
            </c:if>
            <c:set var="startPage" value="${pageCount>9?currentPage - 3 : 1}"/>
            <c:if test="${startPage<1}">
                <c:set var="startPage" value="1"/>
            </c:if>
            <c:if test="${startPage + 8 > pageCount}">
                <c:set var="startPage" value="${pageCount > 9 ? pageCount - 8 : 1}"/>
            </c:if>
            <c:forEach var="p" begin="${startPage}" end="${pageCount > startPage + 8 ? startPage+8: pageCount}"
                       step="1">
                <li <c:if test="${p == currentPage + 1}">class="current"</c:if>>
                    <button name="${name}" value="${p - 1}" type="submit">${p}</button>
                </li>
            </c:forEach>
            <c:if test="${pageCount>9}">
                <li class="arrow unavailable">
                    <button name="${name}" value="${pageCount - 1}" type="submit" class="btn btn-info">&laquo;</button>
                </li>
            </c:if>
        </ul>
    </sf:form>
</c:if>
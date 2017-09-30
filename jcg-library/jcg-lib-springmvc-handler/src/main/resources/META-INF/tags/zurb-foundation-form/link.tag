<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="filter" uri="http://sk.ir/common/tags/security-filter" %>

<%@ attribute name="path" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="label" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="readonly" required="false" type="java.lang.Boolean" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="placeholder" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="maxlength" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconLeft" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconRight" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="name" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="url" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="idQuery" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>

<c:set var="errors">
    <sf:errors path="${path}" delimiter="<br/>"/>
</c:set>

<c:set var="c" value="input"/>
<c:if test="${not empty iconRight}">
    <c:set var="c" value="${c} addon-right"/>
</c:if>
<c:if test="${not empty iconLeft}">
    <c:set var="c" value="${c} addon-left"/>
</c:if>
<c:if test="${not empty errors}">
    <c:set var="c" value="${c} has-error"/>
</c:if>
<c:if test="${not empty label}">
    <c:set var="c" value="${c} labeled"/>
</c:if>
<div>
    <a href="<filter:hashLongUrl url="${url}" name="id" value="${idQuery}" />"
                  class="primary round button ${cssClass}">
        ${name}
    </a>
</div>
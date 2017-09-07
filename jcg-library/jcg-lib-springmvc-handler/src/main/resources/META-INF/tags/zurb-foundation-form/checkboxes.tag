<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ attribute name="path" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="enums" required="false" rtexprvalue="true" type="ir.sk.jcg.jcglibcommon.localization.LocalizedEnum[]"
              deferredValue="false" %>
<%@ attribute name="newLine" required="false" rtexprvalue="true" type="java.lang.Boolean" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="readonly" required="false" type="java.lang.Boolean" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="label" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="maxlength" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconLeft" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconRight" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>


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
<div class="${c}">
    <c:if test="${not empty errors}">
    <div data-tooltip aria-haspopup="true" class="errors has-tip s tip-right radius" title='${errors}'></c:if>
        <c:if test="${not empty label}">
            <label class="radius primary label" for="${id}">${label}</label>
        </c:if>
        <c:forEach var="e" items="${enums}">
        <span style="white-space:nowrap; vertical-align: middle;">
            <sf:checkbox path="${path}" value="${e.string}" cssClass="control ${cssClass}"
                         label="${e.code}"
                         disabled="${readonly}"/>
        </span>&nbsp;
            <c:if test="${empty newLine or newLine}"><br/></c:if>
        </c:forEach>
        <jsp:doBody/>
        <c:if test="${not empty iconRight}">
            <i class="addon addon-right">
                <i class="${iconRight}"></i>
            </i>
        </c:if>
        <c:if test="${not empty iconLeft}">
            <i class="addon addon-left">
                <i class="${iconLeft}"></i>
            </i>
        </c:if>
        <c:if test="${not empty errors}"></div>
    </c:if>
</div>
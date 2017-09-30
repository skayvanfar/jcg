<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="utils" uri="http://sk.ir/common/tags/utils" %>

<%@ attribute name="path" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="label" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="placeholder" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconLeft" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconRight" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="filterUrl" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="createUrl" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="maxCount" required="false" type="java.lang.Integer" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="suggestions" required="false" type="java.lang.Object" rtexprvalue="true" deferredValue="false" %>

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
<s:bind path="${path}">
    <c:set var="init" value="${utils:json(status.actualValue)}"/>
</s:bind>
<div class="${c}">
    <c:if test="${not empty label}">
        <label class="radius primary label" for="${id}">${label}</label>
    </c:if>
    <c:if test="${not empty errors}">
    <div data-tooltip aria-haspopup="true" class="errors has-tip tip-right radius" title='${errors}'></c:if>
        <ul class="control tags editable ${cssClass}"
            style="${cssStyle}"
            data-text=".new .suggest"
            data-input=".new .input"
                <c:if test="${not empty maxCount}">
                    data-max-count="${maxCount}"
                </c:if>
            data-param-title="${path}"
            data-param-value="${path}"
                <c:if test="${not empty init}">
                    data-init='${init}'
                </c:if>
                >
            <li class="new">
                <input type="text" class="suggest" placeholder="${placeholder}" id="${id}"
                        <c:if test="${not empty suggestions}">
                            data-suggest='${utils:json(suggestions)}'
                        </c:if>
                        <c:if test="${not empty filterUrl}">
                            data-filter="<s:url value="${filterUrl}"/>"
                        </c:if>
                        <c:if test="${not empty createUrl}">
                            data-create="<s:url value="${createUrl}"/>"
                        </c:if>
                        />
            </li>
        </ul>
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
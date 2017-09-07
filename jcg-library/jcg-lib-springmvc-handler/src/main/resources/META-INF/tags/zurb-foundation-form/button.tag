<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ attribute name="name" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="value" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="type" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="label" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="icon" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="confirm" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>


<button class="button ${cssClass}"
        <c:if test="${not empty name}"> name="${name}" </c:if>
        <c:if test="${not empty name}"> value="${value}" </c:if>
        type='${not empty type ? type : "submit"}'
        <c:if test="${not empty id}"> id="${id}" </c:if>
        <c:if test="${not empty cssStyle}"> style="${cssStyle}" </c:if>
        >
    <c:if test="${not empty icon}">
        <i class="${icon}"></i>
    </c:if>
    <c:if test="${not empty label}">
        ${label}
    </c:if>
</button>
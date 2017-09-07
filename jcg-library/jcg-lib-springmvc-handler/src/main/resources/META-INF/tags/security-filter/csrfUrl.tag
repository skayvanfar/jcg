<%@ tag import="ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.FilterConfiguration" %>
<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="url" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>

<%--<%= FilterConfiguration.i().getParamCSRF()  %> <br />--%>
<%--<%= FilterConfiguration.i().getSessionCSRF() %> <br />--%>
<c:set var="name">
    <%= FilterConfiguration.i().getParamCSRF() %>
</c:set>
<c:set var="value">
    <%= request.getSession().getAttribute(FilterConfiguration.i().getSessionCSRF()) %>
</c:set>
<s:url value="${url}">
    <s:param name="${name}"
             value="${value}"/>
</s:url>
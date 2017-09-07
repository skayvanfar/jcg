<%@ tag import="ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.impl.HashedLong" %>
<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="url" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="name" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false"%>
<%@ attribute name="value" required="true" type="java.lang.Long" rtexprvalue="true" deferredValue="false"%>

<c:set var="encrypted">
    <%= new HashedLong(value).getEncrypted() %>
</c:set>
<s:url value="${url}">
    <s:param name="${name}"
             value="${encrypted}"/>
</s:url>
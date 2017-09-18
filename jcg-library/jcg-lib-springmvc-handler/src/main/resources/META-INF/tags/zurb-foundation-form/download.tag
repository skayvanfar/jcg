<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%@ attribute name="location" required="true" rtexprvalue="true" type="ir.sk.jcg.lib.jcglibspringmvchandler.file.web.FileItem"
              deferredValue="false" %>

<c:set var="urn">/download/file</c:set>
<c:forEach items="${location.urn}" var="u">
    <c:set var="urn" value="${urn}/${u}"/>
</c:forEach>

<s:url value="${urn}"/>
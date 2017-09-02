<%@ tag import="java.util.UUID" %>
<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://sk.ir/jcg/zurb-foundation-form" %>

<%@ attribute name="location" required="false" rtexprvalue="true" type="ir.sk.jcg.common.persistence.file.web.FileItem"
              deferredValue="false" %>
<%@ attribute name="tempLocation" required="false" rtexprvalue="true" type="java.lang.String" deferredValue="false" %>
<%@ attribute name="width" required="true" rtexprvalue="true" type="java.lang.String" deferredValue="false" %>
<%@ attribute name="height" required="true" rtexprvalue="true" type="java.lang.String" deferredValue="false" %>
<%@ attribute name="name" required="true" rtexprvalue="true" type="java.lang.String" deferredValue="false" %>

<c:set var="uuid">
    <%= UUID.randomUUID() %>
</c:set>
<div style="width: 100%; text-align: center">
    <div id="${uuid}" class="cropme" style="display: inline-block; width: ${width}; height: ${height};"
         data-name="${name}">
        <c:if test="${not empty tempLocation}">
            <img src="<f:tempDownload location="${tempLocation}"/>">
            <input value="${tempLocation}" name="${name}" type="hidden">
        </c:if>
        <c:if test="${empty tempLocation and not empty location}">
            <img src="<f:download location="${location}"/>">
        </c:if>
    </div>
</div>
<%--<script>--%>
<%--document.body.onload = function () {--%>
<%--$('.cropme').simpleCropper('<s:url value="/uploadImageData"/>');--%>
<%--};--%>
<%--</script>--%>
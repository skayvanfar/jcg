<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="filter" uri="http://sk.ir/common/tags/security-filter" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="modelAttribute" type="java.lang.String" required="false" rtexprvalue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="method" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="action" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="enctype" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="succeed" required="false" type="java.lang.Boolean" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>


<c:if test="${empty method}"><c:set var="method" value="post"/></c:if>
<c:set var="isUpload" value="${fn:toLowerCase(enctype) eq 'multipart/form-data'}"/>

<c:if test="${isUpload}">
    <c:set var="actionUrl">
        <filter:csrfUrl url="${action}"/>
    </c:set>
</c:if>

<c:if test="${!isUpload}">
    <s:url value="${action}" var="actionUrl"/>
</c:if>
<sf:form method="${method}" modelAttribute="${modelAttribute}" id="${id}"
         enctype="${enctype}" action="${actionUrl}" cssClass="${cssClass}" cssStyle="${cssStyle}">
    <c:if test="${!isUpload}">
        <filter:csrfHidden/>
    </c:if>
    <legend style="display:none"></legend>
    <%--<c:set var="errors"><sf:errors path="*"/></c:set>--%>
    <%--<c:if test="${not empty errors}">--%>
    <%--<div class="alert alert-danger">--%>
    <%--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
    <%--<i class="glyphicon glyphicon glyphicon-remove-circle"></i><strong> خطا ! </strong>--%>
    <%--مقادیر وارد شده معتبر نمی باشد، موارد نامعتبر در فرم با رنگ قرمز متمایز شده اند.--%>
    <%--</div>--%>
    <%--</c:if>--%>
    <fieldset>
        <jsp:doBody/>
    </fieldset>
</sf:form>
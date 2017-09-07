<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://sk.ir/patienttalk/zurb-foundation-form" %>

<%@ attribute name="path" required="true" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="id" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="label" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssClass" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="cssStyle" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconLeft" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="iconRight" required="false" type="java.lang.String" rtexprvalue="true" deferredValue="false" %>
<%@ attribute name="async" required="false" type="java.lang.Boolean" rtexprvalue="true" deferredValue="false" %>


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
<div class="${c}">
    <div class="progress">
        <c:if test="${not empty label}">
            <label class="radius primary label" for="${id}">${label}</label>
        </c:if>
        <c:if test="${not empty errors}">
        <div data-tooltip aria-haspopup="true" class="errors has-tip tip-right radius" title='${errors}'></c:if>
            <label class="control">فایل مورد نظر خود را انتخاب کنید</label>
            <c:choose>
                <c:when test="${not empty async and async}">
                    <input type="file" name="${path}" id="${id}" style="${cssStyle}" class="file ${cssClass}"
                           data-url="<s:url value="/upload/file" />"/>
                </c:when>
                <c:otherwise>
                    <input type="file" name="${path}" id="${id}" style="${cssStyle}" class="file ${cssClass}"/>
                </c:otherwise>
            </c:choose>
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

</div>

<%--<script>--%>
<%--document.body.onload = function () {--%>
<%--var divUUID = "${id}";--%>
<%--$('#' + divUUID).fileupload({--%>
<%--dataType: 'json',--%>

<%--done: function (e, data) {--%>
<%--$("tr:has(td)").remove();--%>
<%--$.each(data.result, function (index, file) {--%>
<%--$("#" + divUUID + "-uploaded").val(file.fileName);--%>
<%--$("#" + divUUID + "-filename").text(file.originalFileName + " آپلود شد!");--%>
<%--});--%>
<%--},--%>

<%--progressall: function (e, data) {--%>
<%--var progress = parseInt(data.loaded / data.total * 100, 10);--%>
<%--$('#' + divUUID + '-progress .meter').css(--%>
<%--'width',--%>
<%--progress + '%'--%>
<%--);--%>
<%--},--%>

<%--dropZone: $('#' + divUUID + '-dropzone')--%>
<%--});--%>
<%--};--%>
<%--</script>--%>
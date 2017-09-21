<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ attribute name="arrival" type="java.lang.String" required="true" rtexprvalue="true" %>

<dd class="s-top-section" data-magellan-arrival="${arrival}">
    <div class="row medium-12 small-12">
        <div class="columns medium-4 small-4 inline-menu-icons">
            <img src="<s:url value="/resources/images/favicon.ico"/>"/>
            <span class="icon-text_justify inline-menu-right-icon logo-purple"></span>
        </div>
        <div class="columns medium-4 small-4 inline-menu-center">
            <jsp:doBody/>
        </div>
        <div class="columns medium-4 small-4 inline-menu-icons">
            <span class="icon-basket inline-menu-left-icon inline-menu-basket basket-green"></span>
            <span class="icon-magnifying_glass inline-menu-left-icon find-blue"></span>
        </div>
    </div>
</dd>
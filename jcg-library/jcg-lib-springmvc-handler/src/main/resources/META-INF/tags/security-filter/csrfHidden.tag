<%@ tag import="ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.FilterConfiguration" %>
<%@ tag body-content="scriptless" pageEncoding="utf-8"  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden"
       name="<%= FilterConfiguration.i().getParamCSRF() %>"
       value="<%= request.getSession().getAttribute(FilterConfiguration.i().getSessionCSRF()) %>" />
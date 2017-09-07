<%@ tag import="ir.sk.jcg.lib.jcglibspringmvchandler.web.securityFilter.impl.HashedLong" %>
<%@ tag body-content="scriptless" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="value" required="true" type="java.lang.Long" rtexprvalue="true" deferredValue="false" %>
<%= new HashedLong(value).getEncrypted() %>

<%--
  Created by IntelliJ IDEA.
  User: Saeed
  Date: 1/6/2017
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>

<!--[if IE 6]> <html class="ie6" class="no-js" lang="fa" dir="rtl"> <![endif]-->
<!--[if IE 7]> <html class="ie7" class="no-js" lang="fa" dir="rtl"> <![endif]-->
<!--[if IE 8]> <html class="ie8" class="no-js" lang="fa" dir="rtl"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="fa" dir="rtl">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Patient Talk Forum">
    <meta name="keywords" content="Patient, Talk, Forum, Tinnitus">
    <meta name="author" content="Saeed Kayvanfar">
    <title><tiles:insertAttribute name="title"/></title>

    <script src="<c:url value="/resources/js/jquery-3.1.1.min.js" />"></script>
    <%--
        <script src="<c:url value="/resources/js/core.js" />"></script>
    --%>
    <%--<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">--%>

    <link rel="stylesheet" href="<s:url value="/resources/css/normalize.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/foundation/foundation.css" />"/>
<%--
    <link rel="stylesheet" href="<s:url value="/resources/zurb-foundation-6.3.0/css/foundation.min.css" />"/>
--%>
    <link rel="stylesheet" href="<s:url value="/resources/css/main.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/icon.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/font/material-design-icons/material-icons.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/slick/slick.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/top.css" />"/>
<%--
    <link rel="stylesheet" href="<s:url value="/resources/css/recents.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/production.css" />"/>
--%>
    <link rel="stylesheet" href="<s:url value="/resources/css/form.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/cropper/style.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/cropper/jquery.Jcrop.css" />"/>
    <link rel="stylesheet" href="<s:url value="/resources/css/image_gallery/magnific-popup.css" />"/>

    <script src="<s:url value="/resources/js/jquery.js" />"></script>
    <script src="<s:url value="/resources/js/modernizr.js" />"></script>
    <script src="<s:url value="/resources/js/fastclick.js" />"></script>
    <script src="<s:url value="/resources/js/placeholder.js" />"></script>
    <script src="<s:url value="/resources/js/form.js" />"></script>
    <script src="<s:url value="/resources/slick/slick.js" />"></script>
<%--
    <script src="<s:url value="/resources/js/production.js" />"></script>
--%>
    <script src="<s:url value="/resources/js/cropper/jquery.Jcrop.js" />"></script>
    <script src="<s:url value="/resources/js/cropper/jquery.SimpleCropper.js" />"></script>
    <script src="<s:url value="/resources/js/vendor/jquery.ui.widget.js" />"></script>
    <script src="<s:url value="/resources/js/file_upload/jquery.iframe-transport.js" />"></script>
    <script src="<s:url value="/resources/js/file_upload/jquery.fileupload.js" />"></script>
    <script src="<s:url value="/resources/js/image_gallery/jquery.magnific-popup.min.js" />"></script>
    <script src="<s:url value="/resources/js/foundation/foundation.min.js" />"></script>
    <script src="<s:url value="/resources/js/foundation/foundation.equalizer.js" />"></script>
<%--
    <script src="<s:url value="/resources/zurb-foundation-6.3.0/js/vendor/foundation.min.js" />"></script>
--%>
    <!--[if lt IE 9]>
    <script src='<s:url value="/resources/js/html5shiv.min.js" />'></script>
    <script src='<s:url value="/resources/js/respond.min.js" />'></script>
    <![endif]-->
</head>
<body class="main-layout">

<dl class="s-top-bar" data-magellan-expedition
    data-options="throttle_delay:0;offset_by_height:false;">
    <tiles:insertAttribute name="top"/>
</dl>
<section id="s-wrapper" data-magellan-destination="s-wrapper">
    <header id="s-header">
        <tiles:insertAttribute name="header"/>
    </header>
    <nav id="s-navigation">
        <tiles:insertAttribute name="navigation"/>
    </nav>
    <section id="s-content" data-magellan-destination="s-content" <%--class="row"--%>>
        <tiles:insertAttribute name="content"/>
    </section>
</section>
<footer id="s-footer" data-magellan-destination="s-footer">
    <tiles:insertAttribute name="footer"/>
</footer>
<script>
    $(document).foundation();
</script>

<%--<tiles:insertAttribute name="navigation"/>--%>
<%--<div tabindex="-1" id="content" class="bs-docs-header">
    <div class="container">
        &lt;%&ndash;        <s:message code="addProduct.form.productId.label"/>&ndash;%&gt;
        <h1><tiles:insertAttribute name="heading"/></h1>

        <p><tiles:insertAttribute name="tagline"/></p>
    </div>
</div>--%>

</body>
</html>

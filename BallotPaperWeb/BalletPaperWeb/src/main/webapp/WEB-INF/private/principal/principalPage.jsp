<%@ include file="/WEB-INF/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
    <meta name="description" content=""></meta>
    <meta name="author" content=""></meta>
    
    <link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-57x57.png"></link>
	<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-60x60.png"></link>
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-72x72.png"></link>
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-76x76.png"></link>
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-114x114.png"></link>
	<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-120x120.png"></link>
	<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-144x144.png"></link>
	<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-152x152.png"></link>
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-180x180.png"></link>
	<link rel="icon" type="image/png" sizes="192x192"  href="${pageContext.request.contextPath}/resources/img/favicon/android-icon-192x192.png"></link>
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-32x32.png"></link>
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-96x96.png"></link>
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-16x16.png"></link>
	<link rel="manifest" href="${pageContext.request.contextPath}/resources/img/favicon/manifest.json"></link>
	<meta name="msapplication-TileColor" content="#ffffff"></meta>
	<meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/resources/img/favicon/ms-icon-144x144.png"></meta>
	<meta name="theme-color" content="#ffffff"></meta>
    
    
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'></link>
	<link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'></link>
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/font-awesome/css/font-awesome.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/animate.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/creativePrivate.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/bootstrap.min.css" type="text/css"></link>
	
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/tableList.css" rel="stylesheet"></link>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/carousel.css" rel="stylesheet"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/jquery-ui.css"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/ojovial.css"></link>
	<title><spring:message code="welcome.titulo.page" /></title>

</head>
<body>

	<div>
		<tiles:insertAttribute name="cabecera" />
	</div>
	<div>
		<tiles:insertAttribute name="contenidoIzq" />
		<tiles:insertAttribute name="contenidoCentro" />
		<tiles:insertAttribute name="contenidoDcho" />
	</div>
	<div>
		<tiles:insertAttribute name="pie" />
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/tablesList.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.searchable.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-1.11.1.js"></script> --%>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-ui.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/carousel.js"></script> --%>
</body>
</html>
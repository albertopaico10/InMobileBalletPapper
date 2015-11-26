<%@ include file="/WEB-INF/common/taglib.jsp"%>
<html lang="en">
<head>

	<title><spring:message code="public.titulo.login" /></title>
	
	<meta charset="utf-8"></meta>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
	<meta name="description" content=""></meta>
	<meta name="author" content=""></meta>
	
	
	<link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/img/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="${pageContext.request.contextPath}/resources/img/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/img/favicon/favicon-16x16.png">
	<link rel="manifest" href="${pageContext.request.contextPath}/resources/img/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/resources/img/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	
	
	
	<!-- <meta http-equiv="content-type" content="text/html; charset=UTF-8"></meta> -->
	<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta> -->
	<!-- <meta charset="utf-8"></meta> -->
	<!-- <meta name="generator" content="Bootply"></meta> -->
	<!-- <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"></meta> -->
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'></link>
	<link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'></link>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/font-awesome/css/font-awesome.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/animate.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/creative.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/jquery-ui.css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/ojovial.css"></link>
</head>
<body>

	<div>
		<tiles:insertAttribute name="cabecera" />
	</div>
	<div >
		<tiles:insertAttribute name="contenidoIzq" />
		<tiles:insertAttribute name="contenidoCentro" />
		<tiles:insertAttribute name="contenidoDcho" />
	</div>
	<div>
		<tiles:insertAttribute name="foot" />
	</div>
	
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/init/jquery.easing.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/init/jquery.fittext.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/init/wow.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/init/creative.js"></script>
	
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>	
<%-- 	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-ui.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/tablesList.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.searchable.js"></script> --%>
	
</body>
</html>
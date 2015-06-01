<%@ include file="/WEB-INF/common/taglib.jsp"%>
<html lang="en">
<head>

	<title><spring:message code="public.titulo.login" /></title>
	
	<meta charset="utf-8"></meta>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
	<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
	<meta name="description" content=""></meta>
	<meta name="author" content=""></meta>
	
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
		<tiles:insertAttribute name="pie" />
	</div>
	
	

	

</body>
</html>
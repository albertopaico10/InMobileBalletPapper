<%@ include file="/WEB-INF/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
    <meta name="description" content=""></meta>
    <meta name="author" content=""></meta>
    
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'></link>
	<link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'></link>
    
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/font-awesome/css/font-awesome.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/animate.min.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/creativePrivate.css" type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/init/bootstrap.min.css" type="text/css"></link>
	
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<%--     <link href="${pageContext.request.contextPath}/resources/bootstrap/css/logo-nav.css" rel="stylesheet"></link> --%>
<%--     <link href="${pageContext.request.contextPath}/resources/bootstrap/css/foot.css" rel="stylesheet"></link> --%>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/tableList.css" rel="stylesheet"></link>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/carousel.css" rel="stylesheet"></link>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/jquery-ui.css"></link>
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
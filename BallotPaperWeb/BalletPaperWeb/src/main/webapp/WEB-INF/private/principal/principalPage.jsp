<%@ include file="/WEB-INF/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
    <meta name="viewport" content="width=device-width, initial-scale=1"></meta>
    <meta name="description" content=""></meta>
    <meta name="author" content=""></meta>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/logo-nav.css" rel="stylesheet"></link>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/foot.css" rel="stylesheet"></link>
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
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
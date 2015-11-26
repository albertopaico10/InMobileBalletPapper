<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!-- Navigation -->
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand page-scroll" href="#page-top"><img
				src="${pageContext.request.contextPath}/resources/bootstrap/img/logo-OV.png"></img></a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${typeUser == '1' || typeUser == '99'}">
					<li><a href="formBalletPaperUser.htm?idUser=${idUserLogin}"><spring:message
								code="welcome.option.paper" /></a></li>
				</c:if>
				<c:if test="${typeUser == '2' || typeUser == '99'}">
					<li><a
						href="formBalletPaperDistrict.htm?idUser=${idUserLogin}"><spring:message
								code="welcome.option.paper.district" /></a></li>
				</c:if>
				<li><a href="closeSession.htm">Cerrar Sessión</a></li>
			</ul>
		</div>

	</div>
</nav>
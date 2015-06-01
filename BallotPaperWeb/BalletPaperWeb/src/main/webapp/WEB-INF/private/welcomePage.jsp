<%@ include file="/WEB-INF/common/taglib.jsp"%>

<header>
	<div class="header-content">
		<div class="header-content-inner">
			<h1>
				<spring:message code="welcome.title.firstPage" />${messagesSpecific}
				<input type="hidden" id=idUser name="idUser" value="${idUserLogin}"></input>
			</h1>
		</div>
	</div>
</header>

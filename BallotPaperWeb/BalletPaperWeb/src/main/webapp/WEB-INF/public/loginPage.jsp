<%@ include file="/WEB-INF/common/taglib.jsp"%>

<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<html:form method="POST" commandName="loginUsuForm" action="validateUser.htm" id="idLoginUsuForm" novalidate="novalidate">
				<div class="modal-header" id="idTitleLogin">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h1 class="text-center"><spring:message code="page.login.title.login" /></h1>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block">
						<div class="form-group">
							<html:input path="user" maxlength="60"  class="form-control input-lg" id="userName" placeholder="Email"/>
						</div>
						<div class="form-group">
							<html:password path="password" maxlength="24" class="form-control input-lg" id="password" placeholder="Password"/>
						</div>
						<div id="idDivBtnAccept">
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-lg btn-block"><spring:message code="common.value.accept" /></button>
							</div>
						</div>
						<div id="idDivLnkNewCustomer">
							<span class="pull-right"><a href="${pageContext.request.contextPath}/apk/BalletPaper.apk" id="lnkNewCustomer"><spring:message code="login.download.apk" /></a></span>
						</div>
						<c:if test="${messages == 'FAIL_VALIDATION_USER'}">
							<label class="error"><spring:message code="login.faild.validations" /></label>
						</c:if>
						<c:if test="${messages == 'EMAIL_NOT_EXIST'}">
							<label class="error"><spring:message code="login.email.not.exist" /></label>
						</c:if>
						<c:if test="${messages == 'ERROR'}">
							<label class="error"><spring:message code="login.email.error" /></label><br/>
							<label class="error">${messagesSpecific}</label>
						</c:if>
					</form>
				</div>
				<div class="modal-footer">
					<div class="col-md-12">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	$("#idLoginUsuForm").validate({
		rules: {
			user: {
				required: true,
			    email: true
			},
			password: "required"
		},
		messages: {
			user: {
				required: '<spring:message code="maintenance.generic.field.required" />',
			    email: '<spring:message code="maintenance.email.format" />'
			},
			password: '<spring:message code="maintenance.generic.field.required" />'		
		},
        submitHandler: function(form) {
            form.submit();
        }
	});

});
</script>
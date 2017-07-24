<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>Work Item Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">WorkItem</label>
		<div class="col-sm-10">${workitem.workItem}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">File Change</label>
		<div class="col-sm-10">${workitem.fileChange}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Peer Review</label>
		<div class="col-sm-10">${workitem.peerReviewComments}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Impact Analysis</label>
		<div class="col-sm-10">${workitem.impactAnalysis}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Module</label>
		<div class="col-sm-10">${workitem.module}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Application</label>
		<div class="col-sm-10">${workitem.application}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Requester</label>
		<div class="col-sm-10">${workitem.requestor}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Status</label>
		<div class="col-sm-10">${workitem.status}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Team</label>
		<div class="col-sm-10">${workitem.team}</div>
	</div>
	<spring:url value="/" var="urlHome" />
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<a class="btn-lg btn-primary pull-right" href="${urlHome}">back</a>
			</div>
		</div>	

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
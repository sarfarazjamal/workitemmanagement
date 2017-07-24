<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${userForm['new']}">
			<h1>Add Work Item</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Work Item</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/users" var="userActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="userForm" action="${userActionUrl}">
       <form:hidden path="id" />
       
       <spring:bind path="workItem">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">workItem</label>
				<div class="col-sm-10">
					<form:input path="workItem" type="number" class="form-control " id="workItem" placeholder="WorkItem" />
					<form:errors path="workItem" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="fileChange">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">File Changed</label>
				<div class="col-sm-10">
					<form:input path="fileChange" type="text" class="form-control " id="fileChange" placeholder="FileChange" />
					<form:errors path="fileChange" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="peerReviewComments">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Peer Review Comments</label>
				<div class="col-sm-10">
					<form:input path="peerReviewComments" type="text" class="form-control " id="peerReviewComments" placeholder="PeerReviewComments" />
					<form:errors path="peerReviewComments" class="control-label" />
				</div>
			</div>
		</spring:bind>
       
       <spring:bind path="impactAnalysis">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Impact Analysis</label>
				<div class="col-sm-10">
					<form:input path="impactAnalysis" type="text" class="form-control " id="impactAnalysis" placeholder="ImpactAnalysis" />
					<form:errors path="impactAnalysis" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<spring:bind path="module">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Module</label>
				<div class="col-sm-10">
					<form:input path="module" type="text" class="form-control " id="module" placeholder="Module" />
					<form:errors path="module" class="control-label" />
				</div>
			</div>
		</spring:bind>
		

		<spring:bind path="application">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Application</label>
				<div class="col-sm-5">
					<form:select path="application" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${application}" />
					</form:select>
					<form:errors path="application" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>

		<spring:bind path="requestor">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Requester</label>
				<div class="col-sm-10">
					<form:input path="requestor" type="text" class="form-control "
						id="requestor" placeholder="Requestor" />
					<form:errors path="requestor" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="status">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Status</label>
				<div class="col-sm-5">
					<form:select path="status" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${finalStatus}" />
					</form:select>
					<form:errors path="status" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
			</spring:bind>
        <spring:bind path="team">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Team</label>
				<div class="col-sm-5">
					<form:select path="team" class="form-control">
						<form:option value="NONE" label="--- Select ---" />
						<form:options items="${team}" />
					</form:select>
					<form:errors path="team" class="control-label" />
				</div>
				<div class="col-sm-5"></div>
			</div>
		</spring:bind>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${userForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
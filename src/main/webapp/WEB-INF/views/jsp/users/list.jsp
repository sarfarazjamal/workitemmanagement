<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Work Items</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#WorkItem</th>
					<th>FileChange</th>
					<th>ReviewComment</th>
					<th>ImpactAnalysis</th>
					<th>Module</th>
					<th>Application</th>
					<th>Requestor</th>
					<th>Team</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="workitem" items="${workitemlist}">
				<tr>
					<td>${workitem.workItem}</td>
					<td>${workitem.fileChange}</td>
					<td>${workitem.peerReviewComments}</td>
					<td>${workitem.impactAnalysis}</td>
					<td>${workitem.module}</td>
					<td>${workitem.application}</td>
					<td>${workitem.requestor}</td>
					<td>${workitem.status}</td>
					<td>${workitem.team}</td>
					
					<td>
						<spring:url value="/users/${workitem.id}" var="userUrl" />
						<spring:url value="/users/${workitem.id}/delete" var="deleteUrl" /> 
						<spring:url value="/users/${workitem.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${userUrl}'">View</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>
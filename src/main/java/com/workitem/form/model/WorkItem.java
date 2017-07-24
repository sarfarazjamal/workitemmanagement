package com.workitem.form.model;

public class WorkItem {
	
	Integer id;

    Integer workItem;
	
	String fileChange;

	String peerReviewComments;

	String impactAnalysis;

	String module;

	String application;

	String requestor;

	String status;

	String team;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkItem() {
		return workItem;
	}

	public void setWorkItem(Integer workItem) {
		this.workItem = workItem;
	}

	public String getFileChange() {
		return fileChange;
	}

	public void setFileChange(String fileChange) {
		this.fileChange = fileChange;
	}

	public String getPeerReviewComments() {
		return peerReviewComments;
	}

	public void setPeerReviewComments(String peerReviewComments) {
		this.peerReviewComments = peerReviewComments;
	}

	public String getImpactAnalysis() {
		return impactAnalysis;
	}

	public void setImpactAnalysis(String impactAnalysis) {
		this.impactAnalysis = impactAnalysis;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public String toString() {
		return "WorkItem [id=" + id + ", workItem=" + workItem + ", fileChange=" + fileChange + ", peerReviewComments="
				+ peerReviewComments + ", impactAnalysis=" + impactAnalysis + ", module=" + module + ", application="
				+ application + ", requestor=" + requestor + ", status=" + status + ", team=" + team + "]";
	}


}

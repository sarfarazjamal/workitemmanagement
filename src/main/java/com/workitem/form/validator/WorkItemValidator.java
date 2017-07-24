package com.workitem.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.workitem.form.model.User;
import com.workitem.form.model.WorkItem;
import com.workitem.form.service.WorkItemService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class WorkItemValidator implements Validator {

	
	
	@Autowired
	WorkItemService workItemService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		WorkItem workItem = (WorkItem) target;

		if(workItem.getWorkItem()==null || workItem.getWorkItem()<=0){
			errors.rejectValue("workItem", "NotEmpty.userForm.workitem");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileChange", "NotEmpty.userForm.filechange");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "peerReviewComments", "NotEmpty.userForm.peerreviewcomments");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "impactAnalysis", "NotEmpty.userForm.impactanalysis");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "module", "NotEmpty.userForm.module");
		if(workItem.getApplication().equalsIgnoreCase("none")){
			errors.rejectValue("application", "NotEmpty.userForm.application");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestor", "NotEmpty.userForm.requestor");
		if(workItem.getStatus().equalsIgnoreCase("none")){
			errors.rejectValue("status", "NotEmpty.userForm.status");
		}
		if(workItem.getTeam().equalsIgnoreCase("none")){
			errors.rejectValue("team", "NotEmpty.userForm.team");
		}

	}

}
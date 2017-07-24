package com.workitem.form.service;

import java.util.List;

import com.workitem.form.model.User;
import com.workitem.form.model.WorkItem;

public interface WorkItemService {

	WorkItem findById(Integer id);
	
	List<WorkItem> findAll();

	void saveOrUpdate(WorkItem user);
	
	void delete(int id);
	
}
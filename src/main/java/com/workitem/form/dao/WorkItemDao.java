package com.workitem.form.dao;

import java.util.List;

import com.workitem.form.model.User;
import com.workitem.form.model.WorkItem;

public interface WorkItemDao {

	WorkItem findById(Integer id);

	List<WorkItem> findAll();

	void save(WorkItem workItem);

	void update(WorkItem workItem);

	void delete(Integer id);

}
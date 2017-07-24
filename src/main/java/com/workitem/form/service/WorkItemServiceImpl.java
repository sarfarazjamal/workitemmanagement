package com.workitem.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workitem.form.dao.WorkItemDao;
import com.workitem.form.model.WorkItem;

@Service("workItemService")
public class WorkItemServiceImpl implements WorkItemService {

	WorkItemDao workItemDao;

	@Autowired
	public void setUserDao(WorkItemDao workItemDao) {
		this.workItemDao = workItemDao;
	}

	@Override
	public WorkItem findById(Integer id) {
		return workItemDao.findById(id);
	}

	@Override
	public List<WorkItem> findAll() {
		return workItemDao.findAll();
	}

	@Override
	public void saveOrUpdate(WorkItem workItem) {

		if (findById(workItem.getId())==null) {
			workItemDao.save(workItem);
		} else {
			workItemDao.update(workItem);
		}

	}

	@Override
	public void delete(int id) {
		workItemDao.delete(id);
	}

}
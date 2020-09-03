package com.revature.dao;

import java.util.List;

import com.revature.models.ReimbursementStatus;

public interface IReimbursementStatusDAO {

	public List<ReimbursementStatus> findAll();
	public ReimbursementStatus findByDescription(String description);
	public ReimbursementStatus findByID(int status_id);
}

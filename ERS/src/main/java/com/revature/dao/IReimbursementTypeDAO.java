package com.revature.dao;

import java.util.List;

import com.revature.models.ReimbursementType;

public interface IReimbursementTypeDAO {

	public List<ReimbursementType> findAll();
	public ReimbursementType findByTypeID(int type_id);
	public ReimbursementType findByType(String type);
}

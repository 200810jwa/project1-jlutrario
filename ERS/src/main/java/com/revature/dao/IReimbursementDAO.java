package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {

	public List<Reimbursement> findAll();
	public List<Reimbursement> findByAuthor(int user_id);
	public Reimbursement findByID(int id);
	public int insert(Reimbursement r);
	public boolean update(Reimbursement r);
	public boolean delete(int id);
}

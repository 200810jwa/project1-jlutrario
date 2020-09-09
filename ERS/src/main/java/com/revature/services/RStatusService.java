package com.revature.services;

import com.revature.dao.ReimbursementStatusDAO;
import com.revature.models.ReimbursementStatus;

public class RStatusService {

	private ReimbursementStatusDAO rStatDAO = new ReimbursementStatusDAO();
	
	public String getStatus(int id) {
		ReimbursementStatus rs = rStatDAO.findByID(id);
		
		return rs.getDescription();
	}
}

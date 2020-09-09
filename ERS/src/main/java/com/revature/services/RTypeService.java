package com.revature.services;

import com.revature.dao.ReimbursementTypeDAO;
import com.revature.models.ReimbursementType;

public class RTypeService {

	private ReimbursementTypeDAO rTypeDAO = new ReimbursementTypeDAO();
	
	public String getType(int id) {
		ReimbursementType rt = rTypeDAO.findByTypeID(id);
		
		return rt.getType();
	}
}

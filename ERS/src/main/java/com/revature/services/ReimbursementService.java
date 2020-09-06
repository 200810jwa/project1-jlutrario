package com.revature.services;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import com.revature.dao.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.templates.ReimbursementTemplate;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private static Logger log = Logger.getLogger(ReimbursementService.class);
	
	public Reimbursement submit(ReimbursementTemplate rt) {
		Reimbursement r = new Reimbursement(0, rt.getAmount(), LocalDateTime.now(), null, rt.getDescription(), 
				null, rt.getAuthor(), 0, rt.getStatus_id(), rt.getType_id());
		
		int new_id = reimbursementDAO.insert(r);
		if (new_id == 0) {
			log.info("Failed to submit reimbursement.");
			return null;
		}
		r.setId(new_id);
		
		return r;
	}
}

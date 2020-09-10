package com.revature.services;

import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.templates.ReimbursementTemplate;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	private UserDAO userDAO = new UserDAO();
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
	
	public List<Reimbursement> getReimbursements(String username) {
		User u = userDAO.findByUsername(username);
		if (u != null) {
			List<Reimbursement> reimbursements = reimbursementDAO.findByAuthor(u.getId());
			return reimbursements;
		} else {
			log.info("Could not find user by username.");
			return null;
		}
	}
	
	public List<Reimbursement> getPending() {
		List<Reimbursement> all = reimbursementDAO.findAll();
		
		List<Reimbursement> pending = new ArrayList<>();
		
		for (Reimbursement r : all) {
			if (r.getStatus_id() == 1) {
				pending.add(r);
			}
		}
		return pending;
	}
	
	public Reimbursement getReimbursement(int id) {
		Reimbursement r = reimbursementDAO.findByID(id);
		
		if (r == null) {
			log.info("Could not find reimbursement.");
			return null;
		}
		return r;
	}
}

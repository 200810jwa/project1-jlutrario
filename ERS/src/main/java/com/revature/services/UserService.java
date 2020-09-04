package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.models.User;
import com.revature.templates.LoginTemplate;

public class UserService {

	private UserDAO userDAO = new UserDAO();
	private static Logger log = Logger.getLogger(UserService.class);
	
	public User login(LoginTemplate lt) {
		User u = userDAO.findByUsername(lt.getUsername());
		
		if (u == null) {
			log.info("Login attempt failed: username not found.");
			return null;
		}
		if (Integer.toString(lt.getPassword().hashCode()).equals(u.getPassword())) {
			return u;
		}
		log.info("Login attempt failed: incorrect password.");
		return null;
	}
	
	public User register(String username, String password, String firstname, String lastname, 
			String email, int user_role_id) {
		User u = new User(0, username, Integer.toString(password.hashCode()), firstname, lastname, email, user_role_id);
		
		int new_id = userDAO.insert(u);
		
		if (new_id == 0) {
			log.info("Failed to register new user.");
			return null;
		}
		u.setId(new_id);
		
		return u;
	}
}

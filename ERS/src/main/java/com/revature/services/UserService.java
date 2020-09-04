package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.models.User;
import com.revature.templates.LoginTemplate;

public class UserService {

	private UserDAO userDAO = new UserDAO();

	public User login(LoginTemplate lt) {
		User u = userDAO.findByUsername(lt.getUsername());
		
		if (u == null) {
			return null;
		}
		if (lt.getPassword().equals(u.getPassword())) {
			return u;
		}
		
		return null;
	}
}

package com.revature.dao;

import java.util.List;

import com.revature.models.UserRoles;

public interface IUserRolesDAO {

	public List<UserRoles> findAll();
	public UserRoles findByRole(String role);
	public UserRoles findByID(int id);
}

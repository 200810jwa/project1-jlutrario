package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface IUserDAO {

	public List<User> findAll();
	public User findByUsername(String username);
	public User findByEmail(String email);
	public User findByID(int id);
	public int insert(User u);
	public boolean update(User u);
	public boolean delete(int id);
}

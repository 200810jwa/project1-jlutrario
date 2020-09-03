package com.revature.models;

public class UserRoles {

	private int role_id;
	private String role;	// 1: Finance Manager, 2: Employee
	
	public UserRoles() {
		super();
		this.role_id = 0;
		this.role = null;
	}
	
	public UserRoles(int role_id, String role) {
		super();
		this.role_id = role_id;
		this.role = role;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + role_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoles other = (UserRoles) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (role_id != other.role_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRoles [role_id=" + role_id + ", role=" + role + "]";
	}
	
}

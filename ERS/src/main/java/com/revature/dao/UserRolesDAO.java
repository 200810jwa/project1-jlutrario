package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.UserRoles;
import com.revature.utilities.ConnectionUtilities;

public class UserRolesDAO implements IUserRolesDAO {

	private static Logger log = Logger.getLogger(UserRolesDAO.class);
	
	@Override
	public List<UserRoles> findAll() {
		List<UserRoles> allRoles = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM project1.user_roles";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int role_id = rs.getInt("role_id");
				String type = rs.getString("type");

				UserRoles r = new UserRoles(role_id, type);
				allRoles.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all roles");
			return null;
		}

		return allRoles;
	}

	@Override
	public UserRoles findByRole(String role) {
		UserRoles r = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.user_roles WHERE project1.user_roles.role = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, role);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				r = new UserRoles();
				
				r.setRole_id(rs.getInt("role_id"));
				r.setRole(rs.getString("role"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve role");
			return null;
		}

		return r;
	}

	@Override
	public UserRoles findByID(int id) {
		UserRoles r = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.user_roles WHERE project1.user_roles.role_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				r = new UserRoles();
				
				r.setRole_id(rs.getInt("role_id"));
				r.setRole(rs.getString("role"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve role");
			return null;
		}

		return r;
	}

}

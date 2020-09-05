package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.utilities.ConnectionUtilities;

public class UserDAO implements IUserDAO {

	private static Logger log = Logger.getLogger(UserDAO.class);

	@Override
	public List<User> findAll() {
		List<User> allUsers = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM project1.users";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String email = rs.getString("email");
				int user_role_id = rs.getInt("user_role_id");

				User u = new User(id, username, password, firstname, lastname, email, user_role_id);
				allUsers.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all users");
			return null;
		}

		return allUsers;
	}

	@Override
	public User findByUsername(String username) {
		User u = null;

		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.users WHERE project1.users.username = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				u = new User();

				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				u.setUser_role_id(rs.getInt("user_role_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve user.");
			return null;
		}

		return u;
	}

	@Override
	public User findByEmail(String email) {
		User u = null;

		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.users WHERE project1.users.email = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				u = new User();

				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				u.setUser_role_id(rs.getInt("user_role_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve user.");
			return null;
		}

		return u;
	}

	@Override
	public User findByID(int id) {
		User u = null;

		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.users WHERE project1.users.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				u = new User();

				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstname(rs.getString("firstname"));
				u.setLastname(rs.getString("lastname"));
				u.setEmail(rs.getString("email"));
				u.setUser_role_id(rs.getInt("user_role_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve user.");
			return null;
		}

		return u;
	}

	@Override
	public int insert(User u) {
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "INSERT INTO project1.users (username, password, firstname, lastname, email, user_role_id) " + 
			"VALUES (?, ?, ?, ?, ?, ?) RETURNING project1.users.id";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstname());
			stmt.setString(4, u.getLastname());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getUser_role_id());

			ResultSet rs;
			if ((rs = stmt.executeQuery()) != null) {
				rs.next();

				int id = rs.getInt(1);

				log.info("Successfully created a user.");
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to create new user.");
		}
		return 0;
	}

	@Override
	public boolean update(User u) {
		try (Connection conn = ConnectionUtilities.getConnection()) {
			
			String sql = "UPDATE project1.users SET username = ?, password = ?, firstname = ?, lastname = ?, " +
			"email = ?, user_role_id = ? WHERE project1.users.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstname());
			stmt.setString(4, u.getLastname());
			stmt.setString(5, u.getEmail());
			stmt.setInt(6, u.getUser_role_id());
			stmt.setInt(7, u.getId());
			
			if (stmt.executeUpdate() != 0) {
				log.info("Successfully updated a user.");
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to update user.");
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection conn = ConnectionUtilities.getConnection()) {
			
			String sql = "DELETE project1.users WHERE project1.users.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			if (stmt.executeUpdate() != 0) {
				log.info("Successfully deleted a user.");
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to delete user.");
		}
		return false;
	}

}

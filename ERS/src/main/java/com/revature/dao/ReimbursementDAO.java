package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.utilities.ConnectionUtilities;

public class ReimbursementDAO implements IReimbursementDAO {

	private static Logger log = Logger.getLogger(ReimbursementDAO.class);

	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> allReimbursements = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM project1.reimbursement";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				LocalDateTime submitted = rs.getObject("submitted", LocalDateTime.class);
				LocalDateTime resolved = rs.getObject("resolved", LocalDateTime.class);
				String description = rs.getString("description");
				byte[] receipt = rs.getBytes("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status_id = rs.getInt("status_id");
				int type_id = rs.getInt("type_id");

				Reimbursement r = new Reimbursement(id, amount, submitted, resolved, description, receipt, author,
						resolver, status_id, type_id);
				allReimbursements.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all reimbursements");
			return null;
		}

		return allReimbursements;
	}

	@Override
	public List<Reimbursement> findByAuthor(int user_id) {
		List<Reimbursement> reimbursements = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement WHERE project1.reimbursement.author = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, user_id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				double amount = rs.getDouble("amount");
				LocalDateTime submitted = rs.getObject("submitted", LocalDateTime.class);
				LocalDateTime resolved = rs.getObject("resolved", LocalDateTime.class);
				String description = rs.getString("description");
				byte[] receipt = rs.getBytes("receipt");
				int author = rs.getInt("author");
				int resolver = rs.getInt("resolver");
				int status_id = rs.getInt("status_id");
				int type_id = rs.getInt("type_id");

				Reimbursement r = new Reimbursement(id, amount, submitted, resolved, description, receipt, author,
						resolver, status_id, type_id);
				reimbursements.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve reimbursements");
			return null;
		}
		return reimbursements;
	}

	@Override
	public Reimbursement findByID(int id) {
		Reimbursement r = null;

		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement WHERE project1.reimbursement.author = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				r = new Reimbursement();

				r.setId(rs.getInt("id"));
				r.setAmount(rs.getDouble("amount"));
				r.setSubmitted(rs.getObject("submitted", LocalDateTime.class));
				r.setResolved(rs.getObject("resolved", LocalDateTime.class));
				r.setDescription(rs.getString("description"));
				r.setReceipt(rs.getBytes("receipt"));
				r.setAuthor(rs.getInt("author"));
				r.setResolver(rs.getInt("resolver"));
				r.setStatus_id(rs.getInt("status_id"));
				r.setType_id(rs.getInt("type_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve reimbursements");
			return null;
		}
		return r;
	}

	@Override
	public int insert(Reimbursement r) {
		String sql = "INSERT INTO project1.reimbursement (amount, submitted, description, receipt, "
				+ "author, resolver, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING project1.reimbursement.id";

		try (Connection conn = ConnectionUtilities.getConnection()) {

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, r.getAmount());
			stmt.setTimestamp(2, Timestamp.valueOf(r.getSubmitted()));
			stmt.setString(3, r.getDescription());
			stmt.setBytes(4, r.getReceipt());
			stmt.setInt(5, r.getAuthor());
			stmt.setInt(6, r.getResolver());
			stmt.setInt(7, r.getStatus_id());
			stmt.setInt(8, r.getType_id());

			ResultSet rs;
			if ((rs = stmt.executeQuery()) != null) {
				rs.next();

				int id = rs.getInt(1);

				log.info("Successfully submitted reimbursement.");
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to submit reimbursement.");
		}
		return 0;
	}

	@Override
	public boolean update(Reimbursement r) {
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "UPDATE project1.reimbursement SET amount = ?, submitted = ?, resolved = ?, description = ?, "
					+ "receipt = ?, author = ?, resolver = ?, status_id = ?, type_id = ? WHERE project1.reimbursement.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, r.getAmount());
			stmt.setTimestamp(2, Timestamp.valueOf(r.getSubmitted()));
			stmt.setTimestamp(3, Timestamp.valueOf(r.getResolved()));
			stmt.setString(4, r.getDescription());
			stmt.setBytes(5, r.getReceipt());
			stmt.setInt(6, r.getAuthor());
			stmt.setInt(7, r.getResolver());
			stmt.setInt(8, r.getStatus_id());
			stmt.setInt(9, r.getType_id());
			stmt.setInt(10, r.getId());

			if (stmt.executeUpdate() != 0) {
				log.info("Successfully updated reimbursement.");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to update reimbursement.");
		}
		return false;

	}

	@Override
	public boolean delete(int id) {
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "DELETE FROM project1.reimbursement WHERE project1.reimbursement.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			if (stmt.executeUpdate() != 0) {
				log.info("Successfully deleted reimbursement.");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to delete reimbursement.");
		}
		return false;
	}

}

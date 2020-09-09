package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimbursementStatus;
import com.revature.utilities.ConnectionUtilities;

public class ReimbursementStatusDAO implements IReimbursementStatusDAO {

	private static Logger log = Logger.getLogger(ReimbursementStatusDAO.class);
	
	@Override
	public List<ReimbursementStatus> findAll() {
		List<ReimbursementStatus> allStatus = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM project1.reimbursement_status";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String description = rs.getString("description");

				ReimbursementStatus  rStat= new ReimbursementStatus(id, description);
				allStatus.add(rStat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all status'");
			return null;
		}

		return allStatus;
	}

	@Override
	public ReimbursementStatus findByDescription(String description) {
		ReimbursementStatus rStat = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement_status WHERE project1.reimbursement_status.description = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, description);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				rStat = new ReimbursementStatus();
				
				rStat.setId(rs.getInt("id"));
				rStat.setDescription(rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve status");
			return null;
		}

		return rStat;
	}

	@Override
	public ReimbursementStatus findByID(int id) {
		ReimbursementStatus rStat = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement_status WHERE project1.reimbursement_status.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				rStat = new ReimbursementStatus();
				
				rStat.setId(rs.getInt("id"));
				rStat.setDescription(rs.getString("description"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve status");
			return null;
		}

		return rStat;
	}

}

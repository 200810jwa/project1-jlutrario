package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimbursementType;
import com.revature.utilities.ConnectionUtilities;

public class ReimbursementTypeDAO implements IReimbursementTypeDAO {

	private static Logger log = Logger.getLogger(ReimbursementTypeDAO.class);
	
	@Override
	public List<ReimbursementType> findAll() {
		List<ReimbursementType> allTypes = new ArrayList<>();

		try (Connection conn = ConnectionUtilities.getConnection()) {

			Statement stmt = conn.createStatement();

			String sql = "SELECT * FROM project1.reimbursement_type";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");

				ReimbursementType t = new ReimbursementType(id, type);
				allTypes.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve all types");
			return null;
		}

		return allTypes;
	}

	@Override
	public ReimbursementType findByTypeID(int id) {
		ReimbursementType t = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement_type WHERE project1.type.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				t = new ReimbursementType();
				
				t.setId(rs.getInt("id"));
				t.setType(rs.getString("type"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve type");
			return null;
		}

		return t;
	}

	@Override
	public ReimbursementType findByType(String type) {
		ReimbursementType rt = null;
		
		try (Connection conn = ConnectionUtilities.getConnection()) {

			String sql = "SELECT * FROM project1.reimbursement_type WHERE project1.reimbursement_type.type = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				rt = new ReimbursementType();
				
				rt.setId(rs.getInt("id"));
				rt.setType(rs.getString("type"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.info("Failed to retrieve type");
			return null;
		}

		return rt;
	}

}

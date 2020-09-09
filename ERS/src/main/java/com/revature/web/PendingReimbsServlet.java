package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.models.Reimbursement;
import com.revature.services.RTypeService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

/**
 * Servlet implementation class PendingReimbsServlet
 */
@WebServlet(urlPatterns = {"/pendingreimbs"})
public class PendingReimbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ReimbursementService rs = new ReimbursementService();
	private static RTypeService rTypeServ = new RTypeService();
	private static UserService userServ = new UserService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingReimbsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Reimbursement> reimbursements = rs.getPending();
		PrintWriter writer = response.getWriter();
		
		if (reimbursements == null) {
			response.setStatus(400);
		} else {
			JSONArray arr = new JSONArray();
			for (int i = 0; i < reimbursements.size(); i++) {
				JSONObject json = new JSONObject();
						json.put("id", reimbursements.get(i).getId());
						json.put("amount", reimbursements.get(i).getAmount());
						json.put("submitted", reimbursements.get(i).getSubmitted().toString());
						json.put("type", rTypeServ.getType(reimbursements.get(i).getType_id()));
						json.put("author", userServ.authorName(reimbursements.get(i).getAuthor()));
		                arr.put(json);
			}
			
			writer.print(arr);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

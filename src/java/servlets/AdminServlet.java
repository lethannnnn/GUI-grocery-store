/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import da.AdminDao;
import domain.Admin;
import domain.Message;
import helper.ConnectionProvider;

/**
 *
 * @author Ethan
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		AdminDao adminDao = new AdminDao(ConnectionProvider.getConnection());
		HttpSession session = request.getSession();
		Message message = null;
		
		if(operation.trim().equals("save")) {
			
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			
			Admin admin = new Admin(username, email, password, phone);
			boolean flag = adminDao.saveAdmin(admin);

			if(flag) {
				message = new Message("New admin register successfully!", "success", "alert-success");
			}else {
				message = new Message("Sorry! Something went wrong lol", "error", "alert-danger");
			}

		}else if(operation.trim().equals("delete")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = adminDao.deleteAdmin(id);
			if(flag) {
				message = new Message("Admin deleted successfully!", "success", "alert-success");
			}else {
				message = new Message("Sorry! Something went wrong", "error", "alert-danger");
			}
		}
		session.setAttribute("message", message);
		response.sendRedirect("display_admin.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
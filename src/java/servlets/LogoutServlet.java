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

import domain.Message;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("user");
		HttpSession session = request.getSession();
		if(user.trim().equals("user")) {
			session.removeAttribute("activeUser");
			Message message = new Message("Logout successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("login.jsp");
		}else if(user.trim().equals("admin")) {
			session.removeAttribute("activeAdmin");
			Message message = new Message("Logout successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("adminLogin.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
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
        
import da.ManagerDao;
import domain.Manager;
import domain.Message;
import helper.ConnectionProvider;

public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			try {
				String password = request.getParameter("password");

				ManagerDao managerDao = new ManagerDao(ConnectionProvider.getConnection());
				Manager manager = managerDao.getManagerByPassword(password);
				
				HttpSession session = request.getSession();
				if (manager != null) {
					session.setAttribute("activeManager", manager);
					response.sendRedirect("manager_home.jsp");
				} else {
                                        Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("manager_login.jsp?password="+password);
				}
			} catch (IOException e) {
				e.printStackTrace();   
                        }
		}
	}
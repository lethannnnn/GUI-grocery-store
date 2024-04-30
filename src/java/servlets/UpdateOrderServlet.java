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

import da.OrderDao;
import da.UserDao;
import domain.Order;
import helper.ConnectionProvider;
import helper.MailMessenger;
/**
 *
 * @author Ethan
 */
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int oid = Integer.parseInt(request.getParameter("oid"));
		String status = request.getParameter("status");
		OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
		orderDao.updateOrderStatus(oid, status);
		if (status.trim().equals("Shipped") || status.trim().equals("Out For Delivery")) {
			Order order = orderDao.getOrderById(oid);
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			MailMessenger.orderShipped(userDao.getUserName(order.getUserId()), userDao.getUserEmail(order.getUserId()),
					order.getOrderId(), order.getDate().toString());
		}
		response.sendRedirect("display_orders.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
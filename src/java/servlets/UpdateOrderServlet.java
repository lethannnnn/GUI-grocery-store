package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.OrderDao;
import domain.Order;
import helper.ConnectionProvider;
import domain.Message;

public class UpdateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the order ID and new status from request parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        // Create an instance of OrderDao to update the order status
        OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
        
        // Update the order status
        orderDao.updateOrderStatus(id, status);

        // Set a success message
        Message message = new Message("Order status updated successfully!", "success", "alert-success");
        request.getSession().setAttribute("message", message);

        // Redirect back to the display_orders.jsp page
        response.sendRedirect("display_orders.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward GET requests to the doPost method
        doPost(request, response);
    }
}

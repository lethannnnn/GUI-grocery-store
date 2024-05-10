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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get the order ID and new status from request parameters
    String orderid = request.getParameter("orderid"); // Check if "orderid" is the correct parameter name
    String status = request.getParameter("status"); // Ensure "status" is the correct parameter name

    // Create an instance of OrderDao to update the order status
    OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
    
    // Update the order status
    orderDao.updateOrderStatus(orderid, status); // Make sure orderid and status are correctly passed

    // Set a success message
    String successMessage = String.format("Order status updated successfully! Order ID: %s, Status: %s", orderid, status);
    Message message = new Message(successMessage, "success", "alert-success");
    request.getSession().setAttribute("message", message);

    // Redirect back to the display_orders.jsp page
    response.sendRedirect("display_orders.jsp");
}

}

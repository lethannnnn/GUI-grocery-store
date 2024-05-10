<%@page import="domain.Message"%>
<%@page import="domain.OrderedProduct"%>
<%@page import="domain.Order"%>
<%@page import="java.util.List"%>
<%@page import="da.OrderedProductDao"%>
<%@page import="da.OrderDao"%>
<%@page import="helper.ConnectionProvider"%>
<%@page import="domain.User"%>
<%@page errorPage="error_exception.jsp"%>

<%
User u2 = (User) session.getAttribute("activeUser");
if (u2 == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
OrderedProductDao ordProdDao = new OrderedProductDao(ConnectionProvider.getConnection());

List<Order> orderList = orderDao.getAllOrderByUserId(u2.getUserId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Orders</title>
    <!-- Add your CSS links here -->
</head>
<body>
    <div class="container-fluid px-3 py-3">
        <%
            if (orderList == null || orderList.isEmpty()) {
        %>
            <div class="container mt-5 mb-5 text-center">
                <img src="Images/empty-cart.png" style="max-width: 200px;" class="img-fluid">
                <h4 class="mt-3">Zero Orders found</h4>
                <p>Looks like you haven't placed any orders yet!</p>
            </div>
        <%
            } else {
        %>
            <h4>My Orders</h4>
            <hr>
            <div class="container">
                <table class="table table-hover">
                    <tr class="text-center table-secondary">
                        <th>Product</th>
                        <th>Order ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                        <th>Date and Time</th>
                        <th>Payment Type</th>
                        <th>Status</th>
                    </tr>
                    <% for (Order order : orderList) {
                        List<OrderedProduct> ordProdList = ordProdDao.getAllOrderedProduct(order.getId());
                        for (OrderedProduct orderProduct : ordProdList) {
                    %>
                        <tr class="text-center">
                            <td><img src="tmp/<%=orderProduct.getImage()%>" style="width: 40px; height: 40px; width: auto;"></td>
                            <td class="text-start"><%=order.getOrderId()%></td>
                            <td class="text-start"><%=orderProduct.getName()%></td>
                            <td><%=orderProduct.getQuantity()%></td> <!-- Use selected quantity -->
                            <td><%=orderProduct.getPrice() * orderProduct.getQuantity()%></td>
                            <td><%=order.getDate()%></td>
                            <td><%=order.getPaymentType()%></td>
                            <td class="fw-semibold" style="color: green;"><%=order.getStatus()%></td>
                        </tr>
                    <%      }
                        }
                    %>

                </table>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
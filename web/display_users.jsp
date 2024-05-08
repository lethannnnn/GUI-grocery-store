<%@page import="domain.User"%>
<%@page import="domain.Admin"%>
<%@page import="domain.Message"%>
<%@page import="da.UserDao"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminLogin.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View User's</title>
<%@include file="Components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<div class="container-fluid px-5 py-3">
		<table class="table table-hover">
			<tr class="text-center table-primary" style="font-size: 18px;">
				<th>User Name</th>
				<th>Email</th>
				<th>Phone No.</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Register Date</th>
				<th>Action</th>
			</tr>
			<%
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			List<User> userList = userDao.getAllUser();
			for (User u : userList) {
			%>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getUserName()%></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getUserEmail()%></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getUserPhone()%></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getUserGender()%></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=userDao.getUserAddress(u.getUserId())%></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=u.getDateTime()%></td>
				<td><a href="UpdateUserServlet?operation=deleteUser&uid=<%=u.getUserId()%>" role="button" class="btn btn-danger">Remove</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>
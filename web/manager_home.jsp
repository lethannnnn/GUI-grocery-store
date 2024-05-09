<%@page import="domain.Category"%>
<%@page import="domain.Admin"%>
<%@page import="domain.Message"%>
<%@page import="da.AdminDao"%>
<%@page import="da.ManagerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="error_exception.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Page</title>
<%@include file="Components/common_css_js.jsp"%>
<style type="text/css">
.cus-active {
	background-color: #e6eefa !important;
	width: 100%;
}

.list-btn {
	font-size: 18px !important;
}

.list-btn:hover {
	color: #2874f0 !important;
}

.no-border {
	border: 0;
	box-shadow: none;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/login_navbar.jsp"%>

	<!--admin dashboard -->
	<div class="container-fluid py-4 px-3">
		<%@include file="Components/alert_message.jsp"%>
		<div class="row">
			<div class="container text-center" id="details">
				<img src="Images/privacy.png" style="max-width: 180px;"
					class="img-fluid">
				<h3>
					Welcome Dear Manager
				</h3>
			</div>
		</div>
                
		<div class="container">
			<div class="row px-3">
				<div class="col-md-4">
					<a href="report.jsp">
						<div class="card text-bg-light mb-3 text-center">
							<div class="card-body">
								<img src="Images/admin.png" style="max-width: 80px;"
									class="img-fluid">
								<h4 class="card-title mt-3">Report</h4>
							</div>
						</div>
					</a>
				</div>
                                <div class="col-md-4">
					<a href="edit_users.jsp">
						<div class="card text-bg-light mb-3 text-center">
							<div class="card-body">
								<img src="Images/add-admin.png" style="max-width: 80px;"
									class="img-fluid">
								<h4 class="card-title mt-3">Edit users</h4>
							</div>
						</div>
					</a>
				</div>
				<div class="col-md-4">
					<a href="display_admin.jsp">
						<div class="card text-bg-light mb-3 text-center">
							<div class="card-body">
								<img src="Images/add-admin.png" style="max-width: 80px;"
									class="img-fluid">
								<h4 class="card-title mt-3">Edit admin</h4>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<!--end-->

</body>
</html>
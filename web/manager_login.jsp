<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Login</title>
<%@include file="Components/common_css_js.jsp"%>
<style>
label{
	font-weight: bold;
}
</style>
</head>
<body >  

	<!--navbar -->
	<%@include file="Components/login_navbar.jsp"%>

	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body px-5">

						<div class="container text-center">
							<img src="Images/privacy.png" style="max-width: 100px;"
								class="img-fluid">
						</div>
						<h3 class="text-center">Manager Sign-In</h3>
						<%@include file="Components/alert_message.jsp" %>
						
						<!--login-form-->
						<form id="login-form" action="ManagerLoginServlet" method="post">
							<input type="hidden" name="login" value="manager"> 
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input type="password" name="password"
									placeholder="Enter your password" class="form-control" required>
							</div>
							<div id="login-btn" class="container text-center">
								<button type="submit" class="btn btn-outline-primary me-3">Login</button>
							</div>
						</form>
					</div>  

				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<%@include file="Components/common_css_js.jsp"%>
<style>
label {
	font-weight: bold;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<div class="container-fluid mt-4">
		<div class="row g-0">
			<div class="col-md-6 offset-md-3">
				<div class="card">
					<div class="card-body px-5">

						<div class="container text-center">
							<img src="Images/signUp.png" style="max-width: 80px;"
								class="img-fluid">
						</div>
						<h3 class="text-center">Create Account</h3>
						<%@include file="Components/alert_message.jsp"%>

						<!--registration-form-->
						<form id="register-form" action="RegisterServlet" method="post">
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">Your name</label> <input type="text"
										name="user_name" class="form-control"
										placeholder="First and last name" required>
								</div>
								<div class="col-md-6 mt-2">
									<label class="form-label">Email</label> <input type="email"
										name="user_email" placeholder="Email address"
										class="form-control" required>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">Mobile number</label> <input
										type="number" name="user_mobile_no"
										placeholder="Mobile number" class="form-control">
								</div>
								<div class="col-md-6 mt-5">
									<label class="form-label pe-3">Gender</label> <input
										class="form-check-input" type="radio" name="gender"
										value="Male"> <span class="form-check-label pe-3 ps-1">
										Male </span> <input class="form-check-input" type="radio"
										name="gender" value="Female"> <span
										class="form-check-label ps-1"> Female </span>
								</div>
							</div>
							<div class="mt-2">
								<label class="form-label">Address</label> <input type="text"
									name="user_address"
									placeholder="Enter Address(Area and Street))"
									class="form-control" required>
							</div>  
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">City</label> <input
										class="form-control" type="text" name="city"
										placeholder="City/District/Town" required>
								</div>
								<div class="col-md-6 mt-2">
									<label class="form-label">Pincode</label> <input
										class="form-control" type="number" name="pincode"
										placeholder="Pincode" maxlength="6" required>
								</div>  
							</div>
							<div class="row">
								<div class="col-md-6 mt-2">
									<label class="form-label">State</label> <select name="state"
										class="form-select">
										<option selected>--Select State--</option>
										<option value="Perlis">Perlis</option>
										<option value="Kedah">Kedah</option>
										<option value="PulauPinang">Pulau Pinang</option>
										<option value="Perak">Perak</option>
										<option value="Selangor">Selangor</option>
										<option value="KualaLumpur">Kuala Lumpur</option>
										<option value="NegeriSembilan">Negeri Sembilan</option>
										<option value="Melaka">Melaka</option>
										<option value="Johor">Johor</option>
										<option value="Pahang">Pahang</option>
										<option value="Terengganu">Terengganu</option>
										<option value="Kelantan">Kelantan</option>
										<option value="Sarawak">Sarawak</option>
										<option value="Sabah">Sabah</option>
									</select>
								</div>
								<div class="col-md-6 mt-2">
									<label class="form-label">Password</label> <input
										type="password" name="user_password"
										placeholder="Enter Password" class="form-control" required>
								</div>
                                                        </div>

                                                            <div class="row">
                                                                <div class="col-md-6 mt-2">
                                                                    <label class="form-label">Security Question</label>
                                                                    <select name="security_question" class="form-select">
                                                                        <option selected>--Select Security Question--</option>
                                                                        <option value="What is your mother's maiden name?">What is your mother's maiden name?</option>
                                                                        <option value="What is the name of your first pet?">What is the name of your first pet?</option>
                                                                        <option value="What city were you born in?">What city were you born in?</option>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-6 mt-2">
                                                                    <label class="form-label">Security Answer</label>
                                                                    <input type="text" name="security_answer" placeholder="Enter Security Answer" class="form-control" required>
                                                                </div>
                                                            </div>
                                                        </form>
                                                
							<div id="submit-btn" class="container text-center mt-4">
								<button type="submit" class="btn btn-outline-primary me-3">Submit</button>
								<button type="reset" class="btn btn-outline-primary">Reset</button>
							</div>
							<div class="mt-3 text-center">
								<h6>
									Already have an account?<a href="login.jsp"
										style="text-decoration: none"> Sign in</a>
								</h6>
							</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@page import="domain.User"%>
<%@page import="domain.Message"%>
<%@page import="da.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="da.CartDao"%>
<%@page errorPage="error_exception.jsp"%>
<%
User activeUser = (User) session.getAttribute("activeUser");
if (activeUser == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
String from = (String)session.getAttribute("from");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Checkout</title>
<%@include file="Components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<div class="container mt-5" style="font-size: 17px;">
		<div class="row">

			<!-- left column -->
			<div class="col-md-8">
				<div class="card">
					<div class="container px-3 py-3">
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Delivery Address</h4>
							</div>
						</div>
						<div class="mt-3 mb-3">
							<h5><%=user.getUserName()%>
								&nbsp;
								<%=user.getUserPhone()%></h5>
							<%
							StringBuilder str = new StringBuilder();
							str.append(user.getUserAddress() + ", ");
							str.append(user.getUserCity() + ", ");
							str.append(user.getUserPincode() + ", ");
							str.append(user.getUserState());
							out.println(str);
							%>
							<br>
							<div class="text-end">
								<button type="button" class="btn btn-outline-primary"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Change Address</button>
							</div>
						</div>
						<hr>
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Payment Options</h4>
							</div>
						</div>
						<form id="orderForm" action="OrderOperationServlet" method="post">
    <div class="form-check mt-2">
        <input class="form-check-input" type="radio" name="payementMode" value="Card Payment" required>
        <label class="form-check-label">Credit / Debit / ATM card</label><br>
        <div class="mb-3">
            <input class="form-control mt-3" type="text" placeholder="Enter card number" name="cardno" id="cardno">
            <div class="row gx-5">
                <div class="col mt-3">
                    <input class="form-control" type="number" placeholder="Enter CVV" name="cvv" id="cvv">
                </div>
                <div class="col mt-3">
                    <input class="form-control" type="text" placeholder="Valid through i.e '07/23'" id="expiry">
                </div>
            </div>
            <input class="form-control mt-3" type="text" placeholder="Enter card holder name" name="name" id="name">
        </div>
        <input class="form-check-input" type="radio" name="payementMode" value="Cash on Delivery">
        <label class="form-check-label">Cash on Delivery</label>
    </div>
    <div class="text-end">
        <button type="button" onclick="validateForm()" class="btn btn-lg btn-outline-primary mt-3">Place Order</button>
    </div>
</form>
					</div>
				</div>
			</div>
			<!-- end of column -->

			<!-- right column -->
			<div class="col-md-4">
				<div class="card">
					<div class="container px-3 py-3">
						<h4>Price Details</h4>
						<hr>
						<%
                                                    if (from.trim().equals("cart")) {
                                                        CartDao cartDao = new CartDao(ConnectionProvider.getConnection());
                                                        int totalProduct = cartDao.getCartCountByUserId(activeUser.getUserId());
                                                        int totalQuantity = cartDao.getTotalQuantityByUserId(activeUser.getUserId());
                                                        float totalPrice = (Float) session.getAttribute("totalPrice");
                                                        float deliveryCharges = totalPrice >= 1000 ? 0 : 25; // Check if totalPrice is 1000 or more
                                                %>
                                                <table class="table table-borderless">
                                                    <tr>
                                                        <td>Total Item</td>
                                                        <td><%=totalProduct%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Total Quantity</td>
                                                        <td><%=totalQuantity%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Total Price</td>
                                                        <td>&#36; <%=totalPrice%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Delivery Charges</td>
                                                        <td>&#36; <%=deliveryCharges%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Packaging Charges</td>
                                                        <td>&#36; 29</td>
                                                    </tr>
                                                    <tr>
                                                        <td><h5>Amount Payable :</h5></td>
                                                        <td><h5>
                                                                &#36;
                                                                <%=totalPrice + 29 + deliveryCharges%></h5></td>
                                                    </tr>
                                                </table>
						<%
                                                } else {
                                                    ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());
                                                    int pid = (Integer) session.getAttribute("pid");
                                                    float price = productDao.getProductPriceById(pid);
                                                    float deliveryCharges = price >= 1000 ? 0 : 25; // Check if price is 1000 or more
                                                %>
                                                <table class="table table-borderless">
                                                    <tr>
                                                        <td>Total Item</td>
                                                        <td>1</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Total Price</td>
                                                        <td>&#36; <%=price%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Delivery Charges</td>
                                                        <td>&#36; <%=deliveryCharges%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Packaging Charges</td>
                                                        <td>&#36; 29</td>
                                                    </tr>
                                                    <tr>
                                                        <td><h5>Amount Payable :</h5></td>
                                                        <td><h5>
                                                                &#36;
                                                                <%=price + 29 + deliveryCharges%></h5></td>
                                                    </tr>
                                                </table>
                                                <%
                                                }
                                                %>

					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>


	<!--Change Address Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Change
						Address</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="UpdateUserServlet" method="post">
					<input type="hidden" name="operation" value="changeAddress">
					<div class="modal-body mx-3">
						<div class="mt-2">
							<label class="form-label fw-bold">Address</label>
							<textarea name="user_address" rows="3"
								placeholder="Enter Address(Area and Street))"
								class="form-control" required></textarea>
						</div>
						<div class="mt-2">
							<label class="form-label fw-bold">City</label> <input
								class="form-control" type="text" name="city"
								placeholder="City/District/Town" required>
						</div>
						<div class="mt-2">
							<label class="form-label fw-bold">Pincode</label> <input
								class="form-control" type="number" name="pincode"
								placeholder="Pincode" maxlength="6" required>
						</div>
						<div class="mt-2">
							<label class="form-label fw-bold">State</label> <select
								name="state" class="form-select">
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
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end modal -->

</body>
</html>
<script>
    function validateForm() {
        var paymentMode = document.querySelector('input[name="payementMode"]:checked');

        if (!paymentMode) {
            alert("Please select one of the payment options.");
            return false;
        }

        if (paymentMode.value === "Card Payment") {
            var cardno = document.getElementById('cardno').value;
            var cvv = document.getElementById('cvv').value;
            var expiry = document.getElementById('expiry').value;
            var name = document.getElementById('name').value;

            // Card Number Validation (Assuming a 16-digit card number)
            if (cardno.length !== 16 || isNaN(cardno)) {
                alert("Please enter a valid 16-digit card number.");
                return false;
            }

            // CVV Validation (Assuming a 3-digit CVV)
            if (cvv.length !== 3 || isNaN(cvv)) {
                alert("Please enter a valid 3-digit CVV.");
                return false;
            }

            // Expiry Date Validation (Assuming expiry date is in MM/YY format)
            var currentDate = new Date();
            var currentMonth = currentDate.getMonth() + 1;
            var currentYear = currentDate.getFullYear() % 100; // Get last two digits of the year
            var expiryArray = expiry.split('/');
            var expiryMonth = parseInt(expiryArray[0]);
            var expiryYear = parseInt(expiryArray[1]);

            if (expiryArray.length !== 2 || isNaN(expiryMonth) || isNaN(expiryYear)) {
                alert("Please enter a valid expiry date in MM/YY format.");
                return false;
            }

            if (expiryYear < currentYear || (expiryYear === currentYear && expiryMonth < currentMonth)) {
                alert("Please enter a valid expiry date in the future.");
                return false;
            }

            // Card Holder Name Validation
            if (name.trim() === "") {
                alert("Please enter the card holder's name.");
                return false;
            }
        }

        // If all validations pass, submit the form
        document.getElementById('orderForm').submit();
    }
</script>
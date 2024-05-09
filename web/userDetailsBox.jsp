<%@page import="java.util.List"%>
<%@page import="helper.ConnectionProvider"%>
<%@page import="domain.User"%>
<%@page import="domain.Admin"%>
<%@page import="domain.Message"%>
<%@page import="da.UserDao"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
label {
    font-weight: bold;
}
</style>
<form id="update-user" action="EditUserDetailsServlet" method="post">
    <div class="container px-3 py-3">
        <%
        // Get the userId from the URL parameter
        String userIdParam = request.getParameter("userId");
        int userId = 0;
        if (userIdParam != null) {
            userId = Integer.parseInt(userIdParam);
        }

        // Retrieve the user details based on the userId
        UserDao userDao = new UserDao(ConnectionProvider.getConnection());
        User userDetail = userDao.getUserById(userId);
        if (userDetail != null) {
        %>

        <h3>Personal Information</h3>

        <input type="hidden" name="operation" value="updateUserDetails">
        <div class="row">
            <div class="col-md-6 mt-2">
                <label class="form-label">Your name</label>
                <input type="text" name="name" class="form-control" placeholder="First and last name"
                    value="<%=userDetail.getUserName()%>">
            </div>
            <div class="col-md-6 mt-2">
                <label class="form-label">Email</label>
                <input type="email" name="email" placeholder="Email address" class="form-control"
                    value="<%=userDetail.getUserEmail()%>">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mt-2">
                <label class="form-label">Mobile number</label>
                <input type="text" name="mobile_no" placeholder="Mobile number" class="form-control"
                    value="<%=userDetail.getUserPhone()%>">
            </div>
            <div class="col-md-6 mt-5">
                <label class="form-label pe-3">Gender</label>
                <%
                String gender = userDetail.getUserGender();
                if (gender.trim().equals("Male")) {
                %>
                <input class="form-check-input" type="radio" name="gender" value="Male" checked> <span
                    class="form-check-label pe-3 ps-1"> Male </span> <input class="form-check-input" type="radio"
                    name="gender" value="Female"> <span class="form-check-label ps-1"> Female </span>
                <%
                } else {
                %>
                <input class="form-check-input" type="radio" name="gender" value="Male"> <span
                    class="form-check-label pe-3 ps-1"> Male </span> <input class="form-check-input" type="radio"
                    name="gender" value="Female" checked> <span class="form-check-label ps-1"> Female </span>
                <%
                }
                %>
            </div>
        </div>
        <div class="mt-2">
            <label class="form-label">Address</label>
            <input type="text" name="address" placeholder="Enter Address(Area and Street))" class="form-control"
                value="<%=userDetail.getUserAddress()%>">
        </div>
        <div class="row">
            <div class="col-md-6 mt-2">
                <label class="form-label">City</label>
                <input class="form-control" type="text" name="city" placeholder="City/District/Town"
                    value="<%=userDetail.getUserCity()%>">
            </div>
            <div class="col-md-6 mt-2">
                <label class="form-label">Pincode</label>
                <input class="form-control" type="text" name="pincode" placeholder="Pincode" maxlength="6"
                    value="<%= userDetail.getUserPincode() %>">
            </div>
        </div>
        <div class="row mt-2">
            <label class="form-label">State</label>
            <div class="input-group mb-3">
                <select class="form-control" name="state">
                    <% String userState = userDetail.getUserState();
                       if (userState == null || userState.isEmpty()) { %>
                    <option selected>--Select State--</option>
                    <% } else { %>
                    <option>--Select State--</option>
                    <% } %>
                    <option value="Perlis" <%= userState.equals("Perlis") ? "selected" : "" %>>Perlis</option>
                    <option value="Kedah" <%= userState.equals("Kedah") ? "selected" : "" %>>Kedah</option>
                    <option value="PulauPinang" <%= userState.equals("PulauPinang") ? "selected" : "" %>>Pulau
                        Pinang</option>
                    <option value="Perak" <%= userState.equals("Perak") ? "selected" : "" %>>Perak</option>
                    <option value="Selangor" <%= userState.equals("Selangor") ? "selected" : "" %>>Selangor</option>
                    <option value="KualaLumpur" <%= userState.equals("KualaLumpur") ? "selected" : "" %>>Kuala
                        Lumpur</option>
                    <option value="NegeriSembilan" <%= userState.equals("NegeriSembilan") ? "selected" : "" %>>Negeri
                        Sembilan</option>
                    <option value="Melaka" <%= userState.equals("Melaka") ? "selected" : "" %>>Melaka</option>
                    <option value="Johor" <%= userState.equals("Johor") ? "selected" : "" %>>Johor</option>
                    <option value="Pahang" <%= userState.equals("Pahang") ? "selected" : "" %>>Pahang</option>
                    <option value="Terengganu" <%= userState.equals("Terengganu") ? "selected" : "" %>>Terengganu
                    </option>
                    <option value="Kelantan" <%= userState.equals("Kelantan") ? "selected" : "" %>>Kelantan</option>
                    <option value="Sarawak" <%= userState.equals("Sarawak") ? "selected" : "" %>>Sarawak</option>
                    <option value="Sabah" <%= userState.equals("Sabah") ? "selected" : "" %>>Sabah</option>
                </select>
            </div>
        </div>
        
        <div id="submit-btn" class="container text-center mt-3">
            <button type="submit" class="btn btn-outline-primary me-3" >Update</button>
        </div>
    </div>
</form>
<%
}
%>

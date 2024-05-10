package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import da.UserDao;
import domain.Message;
import domain.User;
import helper.ConnectionProvider;
import javax.servlet.http.HttpSession;

public class EditUserDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String userIdParam = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        String userPhone = request.getParameter("mobile_no");
        String userGender = request.getParameter("gender");
        String userAddress = request.getParameter("address");
        String userCity = request.getParameter("city");
        String userPincode = request.getParameter("pincode");
        String userState = request.getParameter("state");
        
        // Update user details in the database
        UserDao userDao = new UserDao(ConnectionProvider.getConnection());
        User userDetail = new User();
        userDetail.setUserId(userId); // Set user ID for the existing user
        userDetail.setUserName(userName);
        userDetail.setUserEmail(userEmail);
        userDetail.setUserPhone(userPhone);
        userDetail.setUserGender(userGender);
        userDetail.setUserAddress(userAddress);
        userDetail.setUserCity(userCity);
        userDetail.setUserPincode(userPincode);
        userDetail.setUserState(userState);
        
        userDao.updateUser(userDetail); // Update user details in the database
        
        // Redirect to userDetails.jsp with updated user data
        response.sendRedirect("edit_users.jsp?userId=" + userId);
    }
}
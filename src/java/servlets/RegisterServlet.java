package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import da.UserDao;
import domain.Message;
import domain.User;
import helper.ConnectionProvider;
import helper.MailMessenger;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String userName = request.getParameter("user_name");
            String userEmail = request.getParameter("user_email");
            String userPassword = request.getParameter("user_password");
            String userPhone = request.getParameter("user_mobile_no");
            String userGender = request.getParameter("gender");
            String userAddress = request.getParameter("user_address");
            String userCity = request.getParameter("city");
            String userPincode = request.getParameter("pincode");
            String userState = request.getParameter("state");
            String securityques = request.getParameter("security_question");
            String securityans = request.getParameter("security_answer");
            
            UserDao userDao = new UserDao(ConnectionProvider.getConnection());
                       
            // Check if the username is already taken
            if (userDao.isUserExists(userName)) {
                String errorMessage = "Username is already taken. Please use another username.";
                Message message = new Message(errorMessage, "error", "alert-danger");
                request.getSession().setAttribute("message", message);
                response.sendRedirect("register.jsp");
                return;
            }

            User user = new User(userName, userEmail, userPassword, userPhone, userGender, userAddress, userCity, userPincode, userState);
            boolean flag = userDao.saveUser(user);

            HttpSession session = request.getSession();
            Message message;
            if (flag) {
                message = new Message("Registration Successful !!", "success", "alert-success");
                MailMessenger.successfullyRegister(userName, userEmail);
            } else {
                String errorMessage = "Invalid details! Try again!!";
                message = new Message(errorMessage, "error", "alert-danger");
            }
            session.setAttribute("message", message);
            response.sendRedirect("register.jsp");
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
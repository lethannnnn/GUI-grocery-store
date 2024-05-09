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
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("operation");
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("activeUser");
        UserDao userDao = new UserDao(ConnectionProvider.getConnection());

        if (op.trim().equals("updateUserDetails")) {
            try {
                String userName = request.getParameter("name");
                String userEmail = request.getParameter("email");
                String userPhone = request.getParameter("mobile_no");
                String userGender = request.getParameter("gender");
                String userAddress = request.getParameter("address");
                String userCity = request.getParameter("city");
                String userPincode = request.getParameter("pincode");
                String userState = request.getParameter("state");
                User user = new User(userName, userEmail, userPhone, userGender, userAddress, userCity,
                        userPincode, userState);
                int userId = Integer.parseInt(request.getParameter("userId"));
               
                userDao.updateUserDetails(user);
                Message message = new Message("User information updated successfully!!", "success", "alert-success");
                session.setAttribute("message", message);
                response.sendRedirect("userDetails.jsp?userId=" + userId);

            } catch (Exception e) {
                e.printStackTrace();
                Message message = new Message("Error updating user information", "error", "alert-danger");
                session.setAttribute("message", message);
                response.sendRedirect("error_page.jsp");
            }

        } else if (op.trim().equals("deleteUser")) {
            int uid = Integer.parseInt(request.getParameter("uid"));
            userDao.deleteUser(uid);
            response.sendRedirect("display_users.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}

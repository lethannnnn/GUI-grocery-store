package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import da.AdminDao;
import da.UserDao;
import domain.Admin;
import domain.Message;
import domain.User;
import helper.ConnectionProvider;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        if (login.trim().equals("user")) {
            try {
                String userEmail = request.getParameter("user_email");
                String userPassword = request.getParameter("user_password");

                // getting user through entered email and passsword
                UserDao userDao = new UserDao(ConnectionProvider.getConnection());
                User user = userDao.getUserByEmailPassword(userEmail, userPassword);

                // storing current user in session
                HttpSession session = request.getSession();
                if (user != null) {
                    session.setAttribute("activeUser", user);
                    response.sendRedirect("index.jsp");
                } else {
                    Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
                    session.setAttribute("message", message);
                    response.sendRedirect("login.jsp");
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        
        } else if (login != null && login.trim().equals("admin")) {
            try {
                String userName = request.getParameter("email");
                String password = request.getParameter("password");

                // Getting admin through entered username and password
                AdminDao adminDao = new AdminDao(ConnectionProvider.getConnection());
                Admin admin = adminDao.getAdminByEmailPassword(userName, password);

                // Storing current admin in session
                HttpSession session = request.getSession();
                if (admin != null) {
                    session.setAttribute("activeAdmin", admin);
                    response.sendRedirect("admin.jsp");
                } else {
                    Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
                    session.setAttribute("message", message);
                    response.sendRedirect("adminLogin.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("adminLogin.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}

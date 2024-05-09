/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Random;
import da.UserDao;
import domain.Message;
import helper.ConnectionProvider;
import helper.MailMessenger;
import javax.servlet.http.HttpSession;
import java.lang.NoClassDefFoundError;
/**
 *
 * @author Ethan
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
            
                
		String referrer = request.getHeader("referer");		
		HttpSession session = request.getSession();

                try{
                    UserDao userDao = new UserDao(ConnectionProvider.getConnection());
                
		if(referrer.contains("forgot_password")) {
			String email = request.getParameter("email").trim();
			List<String> list = userDao.getAllEmail();
			if(list.contains(email)) {
				Random rand = new Random();
				int max = 99999, min = 10000;
				int otp = rand.nextInt(max - min + 1) + min;
				//System.out.println(otp);
				session.setAttribute("otp", otp);
				session.setAttribute("email", email);
				MailMessenger.sendOtp(email, otp);
				
				Message message = new Message("We'ev sent a password reset code to "+email, "success", "alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("otp_code.jsp");
			}else {
				Message message = new Message("Email not found! Try with another email!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("forgot_password.jsp");
				return;
			}
		}else if(referrer.contains("otp_code")) {
			int code = Integer.parseInt(request.getParameter("code"));
			int otp = (int)session.getAttribute("otp");
			if(code == otp) {
				session.removeAttribute("otp");
				response.sendRedirect("change_password.jsp");
			}else {
				Message message = new Message("Invalid verification code entered!", "error", "alert-danger");
				session.setAttribute("message", message);
				response.sendRedirect("otp_code.jsp");
				return;
			}
		}else if(referrer.contains("change_password")) {
			String password = request.getParameter("password");
			String email =(String)session.getAttribute("email");
			userDao.updateUserPasswordByEmail(password, email);
			session.removeAttribute("email");
			
			Message message = new Message("Password updated successfully!", "error", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("login.jsp");
		}
	}catch (Exception e) {
                e.printStackTrace();

        }
    }
}
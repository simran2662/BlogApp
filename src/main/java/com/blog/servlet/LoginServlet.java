package com.blog.servlet;

import com.blog.dao.UserDAO;
import com.blog.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = sanitizeInput(request.getParameter("email"));
        String password = sanitizeInput(request.getParameter("password"));

        try {
            User user = userDAO.validateUser(email);
            if (user != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    if ("Admin".equals(user.getRole())) {
                        response.sendRedirect("admin-dashboard.jsp");
                    } else {
                        response.sendRedirect("viewer-dashboard.jsp");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=invalid");
                }
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String sanitizeInput(String input) {
        return input == null ? "" : input.replaceAll("[<>]", "");
    }
}

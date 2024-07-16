package com.blog.servlet;

import com.blog.dao.BlogPostDAO;
import com.blog.model.BlogPost;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewer-dashboard")
public class ViewerDashboardServlet extends HttpServlet {
    private BlogPostDAO blogPostDAO;

    @Override
    public void init() {
        blogPostDAO = new BlogPostDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<BlogPost> blogPosts = blogPostDAO.getAllPosts();
            request.setAttribute("blogPosts", blogPosts);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("viewer-dashboard.jsp").forward(request, response);
    }
}

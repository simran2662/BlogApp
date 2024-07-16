package com.blog.servlet;

import com.blog.dao.BlogPostDAO;
import com.blog.model.BlogPost;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/view-post")
public class ViewPostServlet extends HttpServlet {
    private BlogPostDAO blogPostDAO;

    @Override
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/BlogDB");
            blogPostDAO = new BlogPostDAO();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            BlogPost post = blogPostDAO.getPostById(id);
            request.setAttribute("post", post);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("view-post.jsp").forward(request, response);
    }
}

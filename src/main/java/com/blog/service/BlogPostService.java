package com.blog.service;

import com.blog.dao.BlogPostDAO;
import com.blog.model.BlogPost;

import java.sql.SQLException;
import java.util.List;

public class BlogPostService {
    private BlogPostDAO blogPostDAO;

    public BlogPostService() {
        blogPostDAO = new BlogPostDAO();
    }

    public void createPost(BlogPost post) throws SQLException, ClassNotFoundException {
        blogPostDAO.createPost(post);
    }

    public List<BlogPost> getAllPosts() throws SQLException, ClassNotFoundException {
        return blogPostDAO.getAllPosts();
    }

    public void updatePost(BlogPost post) throws SQLException, ClassNotFoundException {
        blogPostDAO.updatePost(post);
    }

    public void deletePost(int id) throws SQLException, ClassNotFoundException {
        blogPostDAO.deletePost(id);
    }
    public BlogPost getPostById(int id) throws SQLException, ClassNotFoundException {
		return blogPostDAO.getPostById(id);
        
    }
}

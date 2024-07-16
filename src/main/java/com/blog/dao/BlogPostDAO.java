package com.blog.dao;

import com.blog.model.BlogPost;
import com.blog.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BlogPostDAO {
	 

    public void createPost(BlogPost post) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO blog_posts (title, content, image_path, video_path) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getImagePath());
            statement.setString(4, post.getVideoPath());
            statement.executeUpdate();
        }
    }

    public List<BlogPost> getAllPosts() throws ClassNotFoundException, SQLException  {
        String sql = "SELECT * FROM blog_posts";
        List<BlogPost> posts = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BlogPost post = new BlogPost();
                post.setId(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setImagePath(resultSet.getString("image_path"));
                post.setVideoPath(resultSet.getString("video_path"));
                posts.add(post);
            }
        }
        return posts;
    }

    public void updatePost(BlogPost post) throws ClassNotFoundException, SQLException  {
        String sql = "UPDATE blog_posts SET title = ?, content = ?, image_path = ?, video_path = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getImagePath());
            statement.setString(4, post.getVideoPath());
            statement.setInt(5, post.getId());
            statement.executeUpdate();
        }
    }

    public void deletePost(int id) throws ClassNotFoundException, SQLException  {
        String sql = "DELETE FROM blog_posts WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public BlogPost getPostById(int id) throws ClassNotFoundException, SQLException  {
        String query = "SELECT * FROM blog_posts WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BlogPost post = new BlogPost();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setImagePath(rs.getString("image_path"));
                post.setVideoPath(rs.getString("video_path"));
                return post;
            }
        }
        return null; // Post not found
    }

}

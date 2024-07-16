<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.blog.service.BlogPostService" %>
<%@ page import="com.blog.model.BlogPost" %>
<%
    int postId = Integer.parseInt(request.getParameter("id"));
    BlogPostService blogPostService = new BlogPostService();
    BlogPost post = blogPostService.getPostById(postId);
%>
<html>
<head>
    <title>Edit Post</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Edit Blog Post</h2>
    <form action="editPost" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= post.getId() %>">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="<%= post.getTitle() %>" required>
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control" id="content" name="content" rows="5" required><%= post.getContent() %></textarea>
        </div>
        <div class="form-group">
            <label for="image">Image Upload</label>
            <input type="file" class="form-control" id="image" name="image">
        </div>
        <div class="form-group">
            <label for="video">Video Upload</label>
            <input type="file" class="form-control" id="video" name="video">
        </div>
        <button type="submit" class="btn btn-primary">Update Post</button>
    </form>
</div>
</body>
</html>

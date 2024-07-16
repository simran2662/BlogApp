<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.blog.model.BlogPost" %>
<%@ page import="com.blog.service.BlogPostService" %>
<%
    BlogPostService blogPostService = new BlogPostService();
    List<BlogPost> posts = blogPostService.getAllPosts();
%>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Admin Dashboard</h2>
    <a href="create-post.jsp" class="btn btn-primary">Create New Post</a>
    <table class="table">
        <thead>
            <tr>
                <th>Title</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% for (BlogPost post : posts) { %>
            <tr>
                <td><%= post.getTitle() %></td>
                <td>
                    <form action="editPost" method="get">
                        <input type="hidden" name="id" value="<%= post.getId() %>"/>
                        <input type="submit" value="Edit" class="btn btn-warning"/>
                    </form>
                    <form action="deletePost" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= post.getId() %>"/>
                        <input type="submit" value="Delete" class="btn btn-danger"/>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>

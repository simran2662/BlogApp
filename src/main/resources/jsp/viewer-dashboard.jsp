<!DOCTYPE html>
<html>
<head>
    <title>Viewer Dashboard</title>
</head>
<body>
    <h2>Welcome, Viewer</h2>
    <a href="logout">Logout</a>
    <h3>All Blog Posts</h3>
    <form action="search-posts" method="get">
        <label for="search">Search:</label>
        <input type="text" id="search" name="search">
        <button type="submit">Search</button>
    </form>
    <c:forEach var="post" items="${blogPosts}">
        <div>
            <h4>${post.title}</h4>
            <p>${post.content}</p>
            <a href="view-post.jsp?id=${post.id}">View</a>
        </div>
    </c:forEach>
</body>
</html>

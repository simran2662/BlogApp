<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="register" method="post">
    Name: <input type="text" name="name" required/><br/>
    Email: <input type="text" name="email" required/><br/>
    Password: <input type="password" name="password" required/><br/>
    Role:
    <select name="role">
        <option value="Admin">Admin</option>
        <option value="Viewer">Viewer</option>
    </select><br/>
    <input type="submit" value="Register"/>
</form>
<% if (request.getParameter("error") != null) { %>
    <p style="color:red;"><%= request.getParameter("error") %></p>
<% } %>
<a href="login.jsp">Login</a>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>

    <h2>Login Page</h2>

    <% if ("1".equals(request.getParameter("error"))) { %>
        <p style="color:red;">
            Invalid username or password!
        </p>
    <% } %>

    <form action="${pageContext.request.contextPath}/login"
          method="post">

        <label>Username:</label>
        <input type="text" name="username" required>
        <br><br>

        <label>Password:</label>
        <input type="password" name="password" required>
        <br><br>

        <input type="submit" value="Login">

    </form>

</body>
</html>
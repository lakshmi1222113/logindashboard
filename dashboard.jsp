<%
    if (session.getAttribute("full_name") == null) {
        response.sendRedirect(
            request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
</head>
<body>

    <h2>Welcome,${sessionScope.full_name}!</h2>

    <p>You have successfully logged in.</p>

</body>
</html>
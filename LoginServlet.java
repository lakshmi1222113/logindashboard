package org.example;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String URL =
            "jdbc:mysql://localhost:3306/fsd2_internal";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Max@1234";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String sql = "SELECT full_name FROM users "
                + "WHERE username = ? AND password = ?";

        try {
            // Explicitly load the MySQL JDBC driver;
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    URL, DB_USER, DB_PASSWORD);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        HttpSession session = request.getSession();

                        session.setAttribute(
                                "full_name",
                                rs.getString("full_name")
                        );

                        response.sendRedirect(
                                request.getContextPath() + "/dashboard.jsp"
                        );
                    } else {
                        response.sendRedirect(
                                request.getContextPath()
                                        + "/login.jsp?error=1"
                        );
                    }
                }
            }

        } catch (Exception e) {
            throw new ServletException("Database or login error", e);
        }
    }
}

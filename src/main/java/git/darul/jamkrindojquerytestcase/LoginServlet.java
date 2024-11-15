// src/main/java/git/darul/jamkrindojquerytestcase/LoginServlet.java
package git.darul.jamkrindojquerytestcase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        if ("admin".equals(userID) && "password".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userID", userID);
            response.sendRedirect("welcome");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}

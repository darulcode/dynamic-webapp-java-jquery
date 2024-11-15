<!-- src/main/webapp/login.jsp -->
<form action="login" method="post">
    <label>User ID:</label>
    <input type="text" name="userID">
    <label>Password:</label>
    <input type="password" name="password">
    <button type="submit">Login</button>
    <% if (request.getParameter("error") != null) { %>
    <p style="color: red;">Invalid credentials, please try again.</p>
    <% } %>
</form>

<%@ page import="git.darul.jamkrindojquerytestcase.WelcomeServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $(".student-id").click(function() {
                alert("Student Name: " + $(this).data("name"));
            });
        });
    </script>
</head>
<body>
<h2>Welcome, <%= request.getSession().getAttribute("userID") %></h2>

<table border="1">
    <thead>
    <tr>
        <th>Department</th>
        <th>Student ID</th>
        <th>Marks</th>
        <th>Pass %</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<WelcomeServlet.Student> students = (List<WelcomeServlet.Student>) request.getAttribute("students");
        Map<String, Double> passPercentageByDepartment = (Map<String, Double>) request.getAttribute("passPercentageByDepartment");

        String currentDepartment = "";
        for (WelcomeServlet.Student student : students) {
            if (!student.department.equals(currentDepartment)) {
                currentDepartment = student.department;
                double passPercentage = passPercentageByDepartment.getOrDefault(currentDepartment, 0.0);

                // Tambahkan row baru untuk departemen dan persentase kelulusan
    %>
    <tr>
        <td colspan="3"><b><%= currentDepartment %></b></td>
        <td><b><%= String.format("%.2f", passPercentage) %>%</b></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td><%= student.department %></td>
        <td><a href="#" class="student-id" data-name="<%= student.name %>"><%= student.id %></a></td>
        <td><%= student.marks %></td>
        <td></td> <!-- Kosong karena "Pass %" sudah diisi di atas -->
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

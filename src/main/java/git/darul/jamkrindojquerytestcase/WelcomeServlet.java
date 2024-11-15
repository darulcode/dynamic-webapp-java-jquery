// src/main/java/git/darul/jamkrindojquerytestcase/WelcomeServlet.java
package git.darul.jamkrindojquerytestcase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class WelcomeServlet extends HttpServlet {
    public static class Student {
        public String id;
        public String name;
        public String department;
        public int marks;

        public Student(String id, String name, String department, int marks) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.marks = marks;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = getStudents();
        request.setAttribute("students", students);

        Map<String, Double> passPercentageByDepartment = new HashMap<>();

        Map<String, List<Student>> studentsByDepartment = students.stream()
                .collect(Collectors.groupingBy(student -> student.department));

        for (String department : studentsByDepartment.keySet()) {
            List<Student> deptStudents = studentsByDepartment.get(department);
            long totalStudents = deptStudents.size();
            long passedStudents = deptStudents.stream().filter(student -> student.marks >= 50).count();
            double passPercentage = (passedStudents * 100.0) / totalStudents;
            passPercentageByDepartment.put(department, passPercentage);
        }

        request.setAttribute("passPercentageByDepartment", passPercentageByDepartment);
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    private List<Student> getStudents() {
        return List.of(
                new Student("S1", "Sahrul", "IT", 75),
                new Student("S2", "Wahyu", "IT", 45),
                new Student("S3", "Rohma", "HR", 80),
                new Student("S4", "Maryana", "DS", 46),
                new Student("S5", "Maulana", "HR", 80),
                new Student("S5", "Abdul", "IT", 20)
        );
    }
}

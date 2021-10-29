package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

//    @GetMapping(path= "{studentId}")
//    public Student getStudent(@PathVariable("studentId") Integer studentId) {
//        return STUDENTS.stream()
//                .filter(student -> studentId.equals(student.getStudentId()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException("Student " + studentId + " does not exists"));
//    }

//    public List<Student> selectAllStudents() {
//        String sql = "SELECT id, name FROM students";
//        List<Student> students = jdbcTemplate.query(sql, ((resultSet, i) -> {
//            int id = resultSet.getInt("id");
//            String name = resultSet.getString("name");
//            return new Student(id, name);
//        }));
//        return students;
//    }
//
    @GetMapping(path= "{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId") Integer studentId) {
        String sql = "SELECT id, name FROM students WHERE id = ?";

        Student student = jdbcTemplate.queryForObject(
                sql,
                new Object[]{studentId},
                (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Student(id, name);
        });
        return Optional.ofNullable(student);
    }



}

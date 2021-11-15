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

//    TODO: review need of this connection
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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

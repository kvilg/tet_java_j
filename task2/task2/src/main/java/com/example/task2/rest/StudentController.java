package com.example.task2.rest;



import com.example.task2.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.example.task2.servis.StudentService;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value="/all")
    public ResponseEntity<?> getALlStudents() {
        return service.getALlStudents();
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> addStudent(@RequestBody Student json) {
        return service.add(json);
    }

    @GetMapping(value = "delete")
    public ResponseEntity<?> deleteStudentById(@RequestParam("id") Integer id) {
        return service.deleteById(id);
    }

}

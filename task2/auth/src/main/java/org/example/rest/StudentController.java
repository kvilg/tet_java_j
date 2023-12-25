package org.example.rest;


import org.example.model.StudentModel;
import org.example.servis.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public ResponseEntity<?> addStudent(@RequestBody StudentModel json) throws ParseException {
        return service.add(json);
    }

    @GetMapping(value = "delete")
    public ResponseEntity<?> deleteStudentById(@RequestParam("id") Integer id) {
        return service.deleteById(id);
    }

}

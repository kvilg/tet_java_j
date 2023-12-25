package org.example.servis;


import org.example.entity.Student;
import org.example.model.StudentModel;
import org.example.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public ResponseEntity<?> getALlStudents() {
        List<Student> list = repo.findAllStudent();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<?> add(StudentModel student) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(student.getDateOfBirth(), formatter);

        Student studentNew = new Student(
                student.getFirstName(),
                student.getLastName(),
                student.getPatronymic(),
                date,
                student.getGroupStudent()
        );

        repo.save(studentNew);

//        repo.insertStudent(
//                student.getFirstName(),
//                student.getLastName(),
//                student.getPatronymic()
////                student.getDateOfBirth(),
////                student.getGroup()
//        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteById(Integer id) {
        repo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

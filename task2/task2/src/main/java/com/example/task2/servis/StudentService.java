package com.example.task2.servis;




import com.example.task2.model.entity.Student;
import com.example.task2.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public ResponseEntity<?> getALlStudents() {
        List<Student> list = repo.findAllStudent();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    public ResponseEntity<?> add(Student student){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date date = student.getDateOfBirth();

        repo.insertStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getPatronymic(),
                student.getDateOfBirth(),
                student.getGroup()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> deleteById(Integer id) {
        repo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

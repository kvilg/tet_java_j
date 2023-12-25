package com.example.task2.repo;


import com.example.task2.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Long,Student> {
    @Query(value = "SELECT * FROM Student",
            nativeQuery = true)
    List<Student> findAllStudent();

    @Query(value = "DELETE FROM Student WHERE id = :id",
            nativeQuery = true)
    void deleteById(@Param("id") Integer id);

    @Query(value = """
            INSERT INTO Student (first_name, last_name, patronymic, date_of_birth, group)
            VALUES (':firstName', ':lastName', ':patronymic', ':dateOfBirth', ':group');
            """,nativeQuery = true
    )
    void insertStudent(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("patronymic") String patronymic,
            @Param("dateOfBirth") Date dateOfBirth,
            @Param("group") String group
    );
}

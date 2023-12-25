package org.example.repo;


import org.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query(value = "SELECT * FROM Student",
            nativeQuery = true)
    List<Student> findAllStudent();

    @Query(value = "DELETE FROM Student WHERE id = :id",
            nativeQuery = true)
    boolean deleteById(@Param("id") Integer id);

    @Query(value = """
            INSERT INTO Student (id, first_name, last_name, patronymic)
            VALUES (2,':firstName', ':lastName', ':patronymic');
            """,nativeQuery = true
    )
    boolean insertStudent(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("patronymic") String patronymic
//            ,
//            @Param("dateOfBirth") String dateOfBirth,
//            @Param("group") String group
    );


}

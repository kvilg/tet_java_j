package org.example.model;

import javax.persistence.Column;
import java.sql.Date;

public class StudentModel {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String dateOfBirth;
    private String groupStudent;

    public StudentModel() {
    }

    public StudentModel(String firstName, String lastName, String patronymic, String dateOfBirth, String groupStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.groupStudent = groupStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(String groupStudent) {
        this.groupStudent = groupStudent;
    }
}

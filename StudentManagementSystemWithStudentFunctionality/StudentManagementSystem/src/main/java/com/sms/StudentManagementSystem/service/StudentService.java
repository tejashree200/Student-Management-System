package com.sms.StudentManagementSystem.service;

import com.sms.StudentManagementSystem.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getALlStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student getStudentByEmail(String email);
    Student updateStudent(Student student);

    void deleteStudentById(Long id);
}

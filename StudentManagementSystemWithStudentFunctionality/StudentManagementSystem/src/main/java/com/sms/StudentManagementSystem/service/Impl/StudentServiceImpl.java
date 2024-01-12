package com.sms.StudentManagementSystem.service.Impl;

import com.sms.StudentManagementSystem.entity.Student;
import com.sms.StudentManagementSystem.repository.StudentRepository;
import com.sms.StudentManagementSystem.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getALlStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student getStudentByEmail(String email) {
        List<Student> students = studentRepository.findAll();
        for (Student student:students) {
            if(student.getEmail().compareTo(email) == 0){
                return student;
            }
        }
        return null;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}

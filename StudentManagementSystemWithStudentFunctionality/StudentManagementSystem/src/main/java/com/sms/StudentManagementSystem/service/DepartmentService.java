package com.sms.StudentManagementSystem.service;

import com.sms.StudentManagementSystem.entity.Department;
import com.sms.StudentManagementSystem.entity.Student;

import java.util.List;

public interface DepartmentService {

    List<Student> getALlStudents(Long dept_id);
    List<Department> getAlldepartments();
    Department saveDepartment(Department department);
    Department getDepartmentById(Long id);
    Department updateDepartment(Department department);
    void deleteDepartmentById(Long id);
}

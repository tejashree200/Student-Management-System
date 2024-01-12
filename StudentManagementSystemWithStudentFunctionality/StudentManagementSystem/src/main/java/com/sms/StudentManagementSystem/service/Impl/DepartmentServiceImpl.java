package com.sms.StudentManagementSystem.service.Impl;

import com.sms.StudentManagementSystem.entity.Department;
import com.sms.StudentManagementSystem.entity.Student;
import com.sms.StudentManagementSystem.repository.DepartmentRepository;
import com.sms.StudentManagementSystem.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Student> getALlStudents(Long dept_id) {
        return null;
    }

    @Override
    public List<Department> getAlldepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}

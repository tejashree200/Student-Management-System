package com.sms.StudentManagementSystem.controller;

import com.sms.StudentManagementSystem.entity.Course;
import com.sms.StudentManagementSystem.entity.Department;
import com.sms.StudentManagementSystem.service.CourseService;
import com.sms.StudentManagementSystem.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    private String listDepartments(Model model){
        model.addAttribute("departments",departmentService.getAlldepartments());
        return "departments";
    }

    @GetMapping("/new")
    public String createDepartmentForm(Model model){
        Department department = new Department();
        model.addAttribute("dept",department);
        return "create_department";
    }

    @PostMapping()
    public String saveDepartment(@ModelAttribute("dept") Department department){
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String editDepartmentForm(@PathVariable Long id, Model model){
        model.addAttribute("dept",departmentService.getDepartmentById(id));
        return "edit_department";
    }

    @PostMapping("/{id}")
    public String updateDepartment(@PathVariable Long id,
                                   @ModelAttribute("dept") Department department){
        Department department1 = departmentService.getDepartmentById(id);
        department1.setName(department.getName());
        //department1.setId(id);

        departmentService.updateDepartment(department1);
        return "redirect:/departments";
    }

    @GetMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

    @GetMapping("/courses/{id}")
    public String allCourses(@PathVariable Long id, Model model){
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("dept",department);
        List<Course> courses = department.getCourses();
        model.addAttribute("courses",courses);
        return "department_detail";
    }
}

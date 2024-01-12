package com.sms.StudentManagementSystem.controller;

import com.sms.StudentManagementSystem.entity.Course;
import com.sms.StudentManagementSystem.entity.Department;
import com.sms.StudentManagementSystem.entity.Student;
import com.sms.StudentManagementSystem.service.CourseService;
import com.sms.StudentManagementSystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
@Autowired
    private CourseService courseService;
@Autowired
    private DepartmentService departmentService;

   public CourseController() {
    }

    public CourseController(CourseService courseService, DepartmentService departmentService) {
        this.courseService = courseService;
        this.departmentService = departmentService;
    }

    @GetMapping("/courses")
    public String listCourses(Model model){
        model.addAttribute("courses",courseService.getALlCourses());
        return "courses";
    }
   @GetMapping("/courses/add")
    public String createCourseForm(@RequestParam("id") Long id, Model model){
        Course theCourse = new Course();
        model.addAttribute("theCourse",theCourse);
        model.addAttribute("id",id);
        return "create_course";
    }

    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("theCourse") Course theCourse, @RequestParam("id") Long id){
        Department department = departmentService.getDepartmentById(id);
        Course course = new Course();
        course.setName(theCourse.getName());
        course.setDepartment(department);
        theCourse.setDepartment(department);
        Course savedCourse = courseService.saveCourse(course);
        department.addCourse(savedCourse);

        departmentService.saveDepartment(department);
        //return "redirect:/courses/"+id;
        return "redirect:/departments";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable Long id,Model model){
        model.addAttribute("course",courseService.getCourseById(id));
        return "edit_course";
    }

    @PostMapping("/courses/edit/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute("course") Course course,
                               Model model){
        Course course1 = courseService.getCourseById(id);
        course1.setName(course.getName());
       // System.out.println(course);
        courseService.updateCourse(course1);
        Long deptId = course1.getDepartment().getId();
        System.out.println(deptId);
        return "redirect:/departments/courses/"+deptId;
    }

    @GetMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id){
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/courses/students/{id}")
    public String allCourses(@PathVariable Long id, Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        List<Student> students = course.getStudents();
        model.addAttribute("students",students);
        Department dept = course.getDepartment();
        model.addAttribute("dept",dept);
        return "course_detail";
    }

}

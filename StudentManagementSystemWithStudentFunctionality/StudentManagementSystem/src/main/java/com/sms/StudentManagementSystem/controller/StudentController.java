package com.sms.StudentManagementSystem.controller;

import com.sms.StudentManagementSystem.entity.Course;
import com.sms.StudentManagementSystem.entity.Student;
import com.sms.StudentManagementSystem.service.CourseService;
import com.sms.StudentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public StudentController() {
    }

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getALlStudents());
        return "students";
    }

    @GetMapping("/students/addExisting")
    public String createExistingStudentForm(@RequestParam("id") Long id, Model model){
        Student theStudent = new Student();
        model.addAttribute("theStudent",theStudent);
        model.addAttribute("id",id);
        return "existing_student";
    }
    @PostMapping("/students/saveExisting")
    public String saveExistingStudent(@ModelAttribute("theStudent") Student theStudent, @RequestParam("id") Long id){
        Course course = courseService.getCourseById(id);
        //theStudent.setCourse(course);
        Student student = studentService.getStudentByEmail(theStudent.getEmail());
        student.addCourse(course);
        Student savedStudent = studentService.saveStudent(student);
        course.addStudent(savedStudent);
        courseService.saveCourse(course);

        Long deptId = course.getDepartment().getId();
        return "redirect:/departments/courses/"+deptId;
    }
    @GetMapping("/students/addNew")
    public String createStudentForm(@RequestParam("id") Long id, Model model){
        Student theStudent = new Student();
        model.addAttribute("theStudent",theStudent);
        model.addAttribute("id",id);
        return "create_student";
    }
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("theStudent") Student theStudent, @RequestParam("id") Long id){
        Course course = courseService.getCourseById(id);
        //theStudent.setCourse(course);
      /*  if(studentService.getStudentByEmail(theStudent.getEmail()) != null) {
            Student student = studentService.getStudentByEmail(theStudent.getEmail());
            student.addCourse(course);
            studentService.saveStudent(student);
        }*/
        Student student = new Student();
        student.setFirstname(theStudent.getFirstname());
        student.setLastname(theStudent.getLastname());
        student.setEmail(theStudent.getEmail());
        student.addCourse(course);
        Student savedStudent = studentService.saveStudent(student);
        course.addStudent(savedStudent);
        courseService.saveCourse(course);

        Long deptId = course.getDepartment().getId();
        return "redirect:/departments/courses/"+deptId;
    }/*
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        return "edit_student";
    }
    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model){
        Student student1 = studentService.getStudentById(id);
        student1.setFirstname(student.getFirstname());
        student1.setLastname(student.getLastname());
        student1.setEmail(student.getEmail());

        studentService.updateStudent(student1);
        Long cursId = student1.getCourse().getId();
        return "redirect:/students";
    }*/
    //@GetMapping("/unenroll/{cid}/studentsFromCourse/{id}")
    @RequestMapping(value = "/unenroll/{cid}/{id}", method=RequestMethod.GET)
    public String deleteStudentFromCourse(@PathVariable("id") Long id, @PathVariable("cid") Long cid){
        Course course = courseService.getCourseById((cid));
        Student student = studentService.getStudentById((id));
        course.removeStudent(student);
        student.removeCourse(course);
        Course savedCourse = courseService.saveCourse(course);

        List<Course> courses = student.getCourse();
        if(courses.isEmpty())
            studentService.deleteStudentById((id));

        return "redirect:/students";
    }
    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}

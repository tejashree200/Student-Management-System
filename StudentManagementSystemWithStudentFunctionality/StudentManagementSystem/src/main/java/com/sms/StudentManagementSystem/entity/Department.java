package com.sms.StudentManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dept_name")
    private String name;
   // @OneToMany(mappedBy = "department")
   // private List<Student> students;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

   /* @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private  List<Student> students = new ArrayList<>();
    */
    public Department() {
    }

    public Department(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
        //this.students = students;
    }

    public Department(Long id, String name, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
       // this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

   /* public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
*/
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", courses=" + courses +
                '}';
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

  //  public void addStudent(Student student){ this.students.add(student); }
}

package com.sms.StudentManagementSystem.service;

import com.sms.StudentManagementSystem.entity.Course;
import java.util.List;

public interface CourseService {

    List<Course> getALlCourses();
    Course saveCourse(Course course);
    Course getCourseById(Long id);
    Course updateCourse(Course course);
    void deleteCourseById(Long id);
}

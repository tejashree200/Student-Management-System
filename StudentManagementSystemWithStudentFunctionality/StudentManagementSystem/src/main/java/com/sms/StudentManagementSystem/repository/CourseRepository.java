package com.sms.StudentManagementSystem.repository;

import com.sms.StudentManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}

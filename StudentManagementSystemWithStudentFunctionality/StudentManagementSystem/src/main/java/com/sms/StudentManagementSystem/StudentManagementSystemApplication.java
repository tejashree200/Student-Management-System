package com.sms.StudentManagementSystem;

import com.sms.StudentManagementSystem.entity.Student;
import com.sms.StudentManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception {

	/*	Student student1 = new Student("Ramesh","R","ramesh@gmail.com");
		studentRepository.save(student1);

		Student student2 = new Student("Sanjay","Dutta","sd@gmail.com");
		studentRepository.save(student2);

		Student student3 = new Student("Tony","Stark","jarvis@gmail.com");
		studentRepository.save(student3); */
	}
}

package com.in28minutes.jpa.hibernate.demo;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		 * studentRepository.saveSudentWithPassport();
		 * 
		 * List<Review> reviews = new ArrayList<>();
		 * 
		 * Review review1 = new Review("5", "Great Hands-on Stuff"); Review review2 =
		 * new Review("5", "Hatsoff");
		 * 
		 * reviews.add(review1); reviews.add(review2);
		 * 
		 * courseRepository.addReviewsForCourse(10003L, reviews);
		 * 
		 * studentRepository.insertStudentAndCourse(new Student("Jack"), new
		 * Course("Microservices in 100 Steps"));
*/
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal(10000)));
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal(50)));
		logger.info("All Employees -> {}" + employeeRepository.retrieveAllEmployees());
	}

}

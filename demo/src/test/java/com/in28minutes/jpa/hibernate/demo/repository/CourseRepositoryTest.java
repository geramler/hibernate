package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

// launches spring boot context
@SpringBootTest()
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;

	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}

	@Test
	// Reset DB to initial state after executing the test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10002L);
		Course course = repository.findById(10002L);
		assertNull(course);
	}

	@Test
	@DirtiesContext
	public void save_basic() {
		Course course = repository.save(new Course("AWS in28minutes"));
		Course foundCourse = repository.findById(course.getId());
		assertNotNull(foundCourse);
	}

	@Test
	@DirtiesContext
	public void update_basic() {
		// get course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update course
		course.setName("JPA  in 50 Steps - updated");
		Course updatedCourse = repository.save(course);

		// check value
		Course foundCourse = repository.findById(10001L);
		assertEquals("JPA  in 50 Steps - updated", foundCourse.getName());
	}

	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}", course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
	}

}

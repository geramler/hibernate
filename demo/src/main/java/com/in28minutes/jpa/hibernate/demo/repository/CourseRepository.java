package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

@Repository
// required when modifying/deleting db stored data
// every public method will be run in a single transaction
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	// public Course save(Course course) - insert or update
	public Course save(Course course) {
		// insert
		if (course.getId() == null) {
			em.persist(course);
			// update
		} else {
			em.merge(course);
		}
		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityMananger() {
		Course course1 = new Course("Web Services in 100 Steps");
		em.persist(course1);

		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");
	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);

		logger.info("course.getReviews() -> {}", course.getReviews());

		for (Review review : reviews) {
			review.setCourse(course);
		}

		reviews.stream().forEach(em::persist);
	}

}

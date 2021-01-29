package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

// launches spring boot context
@SpringBootTest()
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(JPQLTest.class);

	@Autowired
	EntityManager em;

	@Test
	public void findAll_basic() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void findByWhere_basic() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_steps_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c  where  c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_courses_with_at_least_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c  where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_courses_order_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	//JOIN => Select c, s from Course c JOIN c.students s
	//LEFT JOIN => Select c, s from Course c LEFT JOIN c.student s
	//CROSS JOIN => Select c, s from Course c, Student s
	@Test
	public void join() {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		// eah element in the list is an array of size 2
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		// eah element in the list is an array of size 2
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		// eah element in the list is an array of size 2
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}
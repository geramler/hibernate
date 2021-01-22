package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
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
		TypedQuery<Course> query = em.createQuery("Select c From Course c", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

	@Test
	public void findByWhere_basic() {
		TypedQuery<Course> query = em.createQuery("Select c From Course c where name like '%100 Steps'", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}

}

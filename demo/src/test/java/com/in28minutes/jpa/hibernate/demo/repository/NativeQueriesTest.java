package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa.hibernate.demo.entity.Course;

@SpringBootTest
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("select * from Course where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("Select * From Course -> {}", resultList);
	}

	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("select * from Course where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("Select * From Course -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("update Course set last_updated_date=sysdate", Course.class);
		int noOfRowsUpdated = query.executeUpdate();
		logger.info("noOfRowUpdated -> {}", noOfRowsUpdated);
	}
	
}

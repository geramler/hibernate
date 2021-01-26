package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
// @Table(name="CourseDetails")
// @NamedQuery(name="query_get_all_courses", query="Select c from Course c")
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "Select c from Course c"),
		@NamedQuery(name = "query_get_100_steps_courses", query = "Select c From Course c where name like '%100 Steps'") })
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(/* name = "fullName", */ nullable = false)
	private String name;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@OneToMany(mappedBy="course"/*, fetch=FetchType.EAGER*/)
	private List<Review> reviews = new ArrayList<>();

	// ensures that other classes which don't inherit from Course
	// will not be able to use the constructor
	// constructor required by JPA
	protected Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

}

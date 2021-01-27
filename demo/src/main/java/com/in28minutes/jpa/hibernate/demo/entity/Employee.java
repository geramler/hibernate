package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// @MappedSuperclass - Parent Entity is unknown in queries
@Entity
// default strategy is SINGLE_TABLE - main concern is performance
// TABLE_PER_CLASS - each concrete subclass is provided with a table
// JOINED - fields that are specific to a subclass are mapped to a separate table - main concern is data integrity
@Inheritance(strategy = InheritanceType.JOINED)
// specifies the name for the Entity Type Column
// @DiscriminatorColumn(name="EmployeeType")
public abstract class Employee {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	// ensures that other classes which don't inherit from Course
	// will not be able to use the constructor
	// no argument constructor required by JPA
	protected Employee() {
	}

	public Employee(String name) {
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

	@Override
	public String toString() {
		return String.format("Employee[%s]", name);
	}

}

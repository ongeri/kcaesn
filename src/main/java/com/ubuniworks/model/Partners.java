package com.ubuniworks.model;
// Generated May 11, 2016 9:12:01 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Partners generated by hbm2java
 */
@Entity
@Table(name = "partners", catalog = "kcaesn")
public class Partners implements java.io.Serializable {

	private Integer idpartners;
	private Idea idea;
	private String name;
	private String role;
	private String description;

	public Partners() {
	}

	public Partners(Idea idea, String name, String role) {
		this.idea = idea;
		this.name = name;
		this.role = role;
	}

	public Partners(Idea idea, String name, String role, String description) {
		this.idea = idea;
		this.name = name;
		this.role = role;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idpartners", unique = true, nullable = false)
	public Integer getIdpartners() {
		return this.idpartners;
	}

	public void setIdpartners(Integer idpartners) {
		this.idpartners = idpartners;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idea_ididea", nullable = false)
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "role", nullable = false)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
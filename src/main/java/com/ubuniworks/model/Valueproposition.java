package com.ubuniworks.model;
// Generated May 11, 2016 9:12:01 PM by Hibernate Tools 5.1.0.Alpha1

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Valueproposition generated by hbm2java
 */
@Entity
@Table(name = "valueproposition", catalog = "kcaesn")
public class Valueproposition implements java.io.Serializable {

	private Integer idvalueproposition;
	private Idea idea;
	private String name;
	private String description;

	public Valueproposition() {
	}

	public Valueproposition(Idea idea, String name, String description) {
		this.idea = idea;
		this.name = name;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idvalueproposition", unique = true, nullable = false)
	public Integer getIdvalueproposition() {
		return this.idvalueproposition;
	}

	public void setIdvalueproposition(Integer idvalueproposition) {
		this.idvalueproposition = idvalueproposition;
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

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

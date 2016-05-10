package com.ubuniworks.model;
// Generated May 4, 2016 2:02:58 PM by Hibernate Tools 5.1.0.Alpha1

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Milestone generated by hbm2java
 */
@Entity
@Table(name = "milestone", catalog = "kcaesn")
@Indexed
@XmlRootElement
public class Milestone implements java.io.Serializable {

	private Integer idmilestone;
	private Idea idea;
	private Milestone parentMilestone;
	private String description;
	private Date duedate;
	private Date datecreated;
	private Set<Milestone> dependentMilestones = new HashSet<Milestone>(0);
	private String name;
	private String status;

	public Milestone() {
	}

	public Milestone(Idea idea, String description, Date duedate, Date datecreated, String name, String status) {
		this.idea = idea;
		this.description = description;
		this.duedate = duedate;
		this.datecreated = datecreated;
		this.name = name;
		this.status = status;
	}

	public Milestone(Idea idea, Milestone parentMilestone, String description, Date duedate, Date datecreated, String name,
			String status, Set<Milestone> dependentMilestones) {
		this.idea = idea;
		this.parentMilestone = parentMilestone;
		this.description = description;
		this.duedate = duedate;
		this.datecreated = datecreated;
		this.name = name;
		this.status = status;
		this.dependentMilestones = dependentMilestones;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
    @DocumentId
	@Column(name = "idmilestone", unique = true, nullable = false)
	public Integer getIdmilestone() {
		return this.idmilestone;
	}

	public void setIdmilestone(Integer idmilestone) {
		this.idmilestone = idmilestone;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ididea", nullable = false)
	public Idea getIdea() {
		return this.idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iddependencymilestone")
    public Milestone getParentMilestone() {
		return this.parentMilestone;
	}

    public void setParentMilestone(Milestone milestone) {
        this.parentMilestone = milestone;
	}

	@Column(name = "description", nullable = false)
    @Field
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "duedate", nullable = false, length = 19)
    @Field
	public Date getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreated", length = 19)
    @Field
	public Date getDatecreated() {
		return this.datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	@Column(name = "status", nullable = false, length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMilestone")
	public Set<Milestone> getDependentMilestones() {
		return this.dependentMilestones;
	}

	public void setDependentMilestones(Set<Milestone> milestones) {
		this.dependentMilestones = milestones;
	}

    @Column(name = "name", nullable = false)
    @Field
	public String getName() {
        return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

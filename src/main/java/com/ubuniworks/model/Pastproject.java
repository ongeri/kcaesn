package com.ubuniworks.model;
// Generated May 10, 2016 7:07:11 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pastproject generated by hbm2java
 */
@Entity
@Table(name = "pastproject", catalog = "kcaesn")
public class Pastproject implements java.io.Serializable {

	private Integer idpastproject;
	private User user;
	private String projectname;
	private String description;
	private Date startdate;
	private Date dateended;

	public Pastproject() {
	}

	public Pastproject(User user, String projectname, String description, Date startdate) {
		this.user = user;
		this.projectname = projectname;
		this.description = description;
		this.startdate = startdate;
	}

	public Pastproject(User user, String projectname, String description, Date startdate, Date dateended) {
		this.user = user;
		this.projectname = projectname;
		this.description = description;
		this.startdate = startdate;
		this.dateended = dateended;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idpastproject", unique = true, nullable = false)
	public Integer getIdpastproject() {
		return this.idpastproject;
	}

	public void setIdpastproject(Integer idpastproject) {
		this.idpastproject = idpastproject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "projectname", nullable = false)
	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	@Column(name = "description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startdate", nullable = false, length = 19)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateended", length = 19)
	public Date getDateended() {
		return this.dateended;
	}

	public void setDateended(Date dateended) {
		this.dateended = dateended;
	}

}

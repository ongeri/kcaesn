package com.ubuniworks.model;
// Generated May 1, 2016 2:35:49 PM by Hibernate Tools 5.1.0.Alpha1

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Idea generated by hbm2java
 */
@Entity
@Table(name = "idea", catalog = "kcaesn")
@Indexed
@XmlRootElement
public class Idea implements java.io.Serializable {

    private Integer ididea;
    private User appUser;
    private Ideabody ideabody;
    private String description;
    private String title;
    private Set<Comment> comments = new HashSet<Comment>(0);
    private Set<Milestone> milestones = new HashSet<Milestone>(0);
    private Set<User> likers = new HashSet<User>(0);

    public Idea() {
    }

    public Idea(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public Idea(User appUser, Ideabody ideabody, String description, String title, Set<User> likers) {
        this.appUser = appUser;
        this.ideabody = ideabody;
        this.description = description;
        this.title = title;
        this.likers = likers;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @DocumentId
    @Column(name = "ididea", unique = true, nullable = false)
    public Integer getIdidea() {
        return this.ididea;
    }

    public void setIdidea(Integer ididea) {
        this.ididea = ididea;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.appUser;
    }

    public void setUser(User appUser) {
        this.appUser = appUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "idideabody")
    public Ideabody getIdeabody() {
        return this.ideabody;
    }

    public void setIdeabody(Ideabody ideabody) {
        this.ideabody = ideabody;
    }

    @Column(name = "description", nullable = false)
    @Field
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "title", nullable = false, length = 45)
    @Field
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idea")
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idea")
    public Set<Milestone> getMilestones() {
        return this.milestones;
    }

    public void setMilestones(Set<Milestone> milestones) {
        this.milestones = milestones;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_likes_idea", catalog = "kcaesn", joinColumns = {
            @JoinColumn(name = "ididea", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)})
    public Set<User> getLikers() {
        return this.likers;
    }

    public void setLikers(Set<User> appUsers) {
        this.likers = appUsers;
    }

}

package com.ubuniworks.model;
// Generated May 1, 2016 2:35:49 PM by Hibernate Tools 5.1.0.Alpha1

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Idea generated by hbm2java
 */
@Entity
@Table(name = "idea", catalog = "kcaesn")
public class Idea implements java.io.Serializable {

    private Integer ididea;
    private User appUser;
    private Ideabody ideabody;
    private String description;
    private String title;
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
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
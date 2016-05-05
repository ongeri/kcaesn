package com.ubuniworks.model;
// Generated May 4, 2016 2:02:58 PM by Hibernate Tools 5.1.0.Alpha1

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "comment", catalog = "kcaesn")
public class Comment implements java.io.Serializable {

    private Integer idcomment;
    private User user;
    private Comment comment;
    private Idea idea;
    private String commenttext;
    private Set<Comment> comments = new HashSet<Comment>(0);
    private String title;

    public Comment() {
    }

    public Comment(User user, Idea idea, String commenttext) {
        this.user = user;
        this.idea = idea;
        this.commenttext = commenttext;
    }

    public Comment(User user, Comment comment, Idea idea, String commenttext, Set<Comment> comments) {
        this.user = user;
        this.comment = comment;
        this.idea = idea;
        this.commenttext = commenttext;
        this.comments = comments;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @DocumentId
    @Column(name = "idcomment", unique = true, nullable = false)
    public Integer getIdcomment() {
        return this.idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentorid", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idparentcomment")
    public Comment getComment() {
        return this.comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ididea", nullable = false)
    public Idea getIdea() {
        return this.idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    @Column(name = "commenttext", nullable = false)
    @Field
    public String getCommenttext() {
        return this.commenttext;
    }

    public void setCommenttext(String commenttext) {
        this.commenttext = commenttext;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    @Column(name = "title", nullable = false)
    @Field
    public void setTitle(String title) {
        this.title = title;
    }
}

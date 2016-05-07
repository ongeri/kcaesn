package com.ubuniworks.dao;

import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;

import java.util.Set;

public interface IdeaDao extends GenericDao<Idea, Integer> {
    Set<Comment> getTopLevelComments(Idea idea);
}

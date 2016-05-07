package com.ubuniworks.dao;

import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Milestone;

import java.util.Set;

public interface IdeaDao extends GenericDao<Idea, Integer> {
    Set<Comment> getTopLevelComments(Idea idea);

    Set<Milestone> getGetMilestones(Idea idea);

    Idea getWithIdeabody(Integer ididea);
}

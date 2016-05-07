package com.ubuniworks.service;

import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;

import java.util.Set;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface IdeaManager extends GenericManager<Idea, Integer> {
    Set<Comment> getTopLevelComments(Idea idea);
}

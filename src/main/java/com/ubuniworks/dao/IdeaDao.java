package com.ubuniworks.dao;

import com.ubuniworks.model.Idea;

/**
 * Role Data Access Object (DAO) interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface IdeaDao extends GenericDao<Idea, Integer> {
    Idea getWithCommentsAndMilestones(Integer id);
}

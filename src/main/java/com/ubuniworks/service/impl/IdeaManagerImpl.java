package com.ubuniworks.service.impl;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Milestone;
import com.ubuniworks.service.IdeaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementation of RoleManager interface.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("ideaManager")
public class IdeaManagerImpl extends GenericManagerImpl<Idea, Integer> implements IdeaManager {
    IdeaDao ideaDao;

    @Autowired
    public void setIdeaDao(IdeaDao ideaDao) {
        this.ideaDao = ideaDao;
        this.dao = ideaDao;
    }

    @Override
    public Set<Comment> getTopLevelComments(Idea idea) {
        return ideaDao.getTopLevelComments(idea);
    }

    @Override
    public Set<Milestone> getMilestones(Idea idea) {
        return ideaDao.getGetMilestones(idea);
    }

    @Override
    public Idea getWithIdeabody(Integer ididea) {
        return ideaDao.getWithIdeabody(ididea);
    }

}
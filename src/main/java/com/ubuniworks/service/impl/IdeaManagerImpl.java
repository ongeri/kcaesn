package com.ubuniworks.service.impl;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.Idea;
import com.ubuniworks.service.IdeaManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of RoleManager interface.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("ideaManager")
public class IdeaManagerImpl extends GenericManagerImpl<Idea, Integer> implements IdeaManager {
    IdeaDao roleDao;

    @Autowired
    public IdeaManagerImpl(IdeaDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public Idea getWithCommentsAndMilestones(Integer id) {
        return roleDao.getWithCommentsAndMilestones(id);
    }
}
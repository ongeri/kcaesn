package com.ubuniworks.service.impl;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.*;
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

    @Override
    public Set<Activities> getActivities(Idea idea) {
        return ideaDao.getActivities(idea);
    }

    @Override
    public Set<Channes> getChannels(Idea idea) {
        return ideaDao.getChannels(idea);
    }

    @Override
    public Set<Coststructure> getCostStructures(Idea idea) {
        return ideaDao.getCostStructures(idea);
    }

    @Override
    public Set<Customerrelationship> getCustomerRelationships(Idea idea) {
        return ideaDao.getCustomerRelationships(idea);
    }

    @Override
    public Set<Customersegment> getCustomerSegments(Idea idea) {
        return ideaDao.getCustomerSegments(idea);
    }

    @Override
    public Set<Revenuestream> getRevenueStreams(Idea idea) {
        return ideaDao.getRevenueStreams(idea);
    }

    @Override
    public Set<Valueproposition> getValuePropositions(Idea idea) {
        return ideaDao.getValuePropositions(idea);
    }

    @Override
    public Set<Partners> getPartners(Idea idea) {
        return ideaDao.getPartners(idea);
    }

    @Override
    public Set<Resources> getResources(Idea idea) {
        return ideaDao.getResources(idea);
    }

}
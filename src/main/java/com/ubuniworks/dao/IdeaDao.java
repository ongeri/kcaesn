package com.ubuniworks.dao;

import com.ubuniworks.model.*;

import java.util.Set;

public interface IdeaDao extends GenericDao<Idea, Integer> {
    Set<Comment> getTopLevelComments(Idea idea);

    Set<Milestone> getGetMilestones(Idea idea);

    Idea getWithIdeabody(Integer ididea);

    Set<Activities> getActivities(Idea idea);

    Set<Channes> getChannels(Idea idea);

    Set<Coststructure> getCostStructures(Idea idea);

    Set<Customerrelationship> getCustomerRelationships(Idea idea);

    Set<Customersegment> getCustomerSegments(Idea idea);

    Set<Revenuestream> getRevenueStreams(Idea idea);

    Set<Valueproposition> getValuePropositions(Idea idea);

    Set<Partners> getPartners(Idea idea);

    Set<Resources> getResources(Idea idea);
}

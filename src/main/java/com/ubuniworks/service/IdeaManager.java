package com.ubuniworks.service;

import com.ubuniworks.model.*;

import java.util.Set;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface IdeaManager extends GenericManager<Idea, Integer> {
    Set<Comment> getTopLevelComments(Idea idea);

    Set<Milestone> getMilestones(Idea idea);

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

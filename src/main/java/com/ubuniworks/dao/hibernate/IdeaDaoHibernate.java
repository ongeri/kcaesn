package com.ubuniworks.dao.hibernate;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Repository
@EnableTransactionManagement
@Transactional
public class IdeaDaoHibernate extends GenericDaoHibernate<Idea, Integer> implements IdeaDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public IdeaDaoHibernate() {
        super(Idea.class);
    }

    @Override
    public Set<Comment> getTopLevelComments(Idea idea) {
        Collection<Comment> comments = getSession().createCriteria(Comment.class).add(Restrictions.eq("idea", idea)).add(Restrictions.isNull("comment")).list();
        Set<Comment> commentSet = new HashSet<>(comments);
        return commentSet;
    }

    @Override
    public Set<Milestone> getGetMilestones(Idea idea) {
        Collection<Milestone> milestones = getSession().createCriteria(Milestone.class).add(Restrictions.eq("idea", idea)).list();
        Set<Milestone> milestoneSet = new HashSet<>(milestones);
        return milestoneSet;
    }

    @Override
    @Transactional
    public Idea getWithIdeabody(Integer ididea) {
        Idea idea = this.get(ididea);
        idea.getIdeabody();
        return idea;
    }

    @Override
    public Set<Activities> getActivities(Idea idea) {
        return new HashSet<Activities>(getSession().createCriteria(Activities.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Channes> getChannels(Idea idea) {
        return new HashSet<Channes>(getSession().createCriteria(Channes.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Coststructure> getCostStructures(Idea idea) {
        return new HashSet<Coststructure>(getSession().createCriteria(Coststructure.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Customerrelationship> getCustomerRelationships(Idea idea) {
        return new HashSet<Customerrelationship>(getSession().createCriteria(Customerrelationship.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Customersegment> getCustomerSegments(Idea idea) {
        return new HashSet<Customersegment>(getSession().createCriteria(Customersegment.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Revenuestream> getRevenueStreams(Idea idea) {
        return new HashSet<Revenuestream>(getSession().createCriteria(Revenuestream.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Valueproposition> getValuePropositions(Idea idea) {
        return new HashSet<Valueproposition>(getSession().createCriteria(Valueproposition.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Partners> getPartners(Idea idea) {
        return new HashSet<Partners>(getSession().createCriteria(Partners.class).add(Restrictions.eq("idea", idea)).list());
    }

    @Override
    public Set<Resources> getResources(Idea idea) {
        return new HashSet<Resources>(getSession().createCriteria(Resources.class).add(Restrictions.eq("idea", idea)).list());
    }
}

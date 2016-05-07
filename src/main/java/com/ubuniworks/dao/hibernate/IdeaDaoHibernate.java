package com.ubuniworks.dao.hibernate;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import com.ubuniworks.model.Milestone;
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
}

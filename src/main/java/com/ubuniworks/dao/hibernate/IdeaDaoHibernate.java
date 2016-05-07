package com.ubuniworks.dao.hibernate;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.Comment;
import com.ubuniworks.model.Idea;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Repository
public class IdeaDaoHibernate extends GenericDaoHibernate<Idea, Integer> implements IdeaDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public IdeaDaoHibernate() {
        super(Idea.class);
    }

    @Override
    public Set<Comment> getTopLevelComments(Idea idea) {
        Collection<Comment> comments = getSession().createCriteria(Comment.class).add(Restrictions.eq("idea", idea)).add(Restrictions.isEmpty("comments")).list();
//        comments = getSession().createQuery("from Comment ");
        Set<Comment> commentSet = new HashSet<>(comments);
        return commentSet;
    }
}

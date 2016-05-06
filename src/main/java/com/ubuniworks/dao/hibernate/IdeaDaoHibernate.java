package com.ubuniworks.dao.hibernate;

import com.ubuniworks.dao.IdeaDao;
import com.ubuniworks.model.Idea;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with hibernate session to save/delete and
 * retrieve Role objects.
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author jgarcia (updated to hibernate 4)
 */
@Repository
public class IdeaDaoHibernate extends GenericDaoHibernate<Idea, Integer> implements IdeaDao {

    public IdeaDaoHibernate(Class<Idea> persistentClass) {
        super(persistentClass);
    }

    @Override
    public Idea getWithCommentsAndMilestones(Integer id) {
        Idea idea;
        idea = get(id);
        idea.getComments();
        idea.getMilestones();
        idea.getIdeabody();
        return idea;
    }
}

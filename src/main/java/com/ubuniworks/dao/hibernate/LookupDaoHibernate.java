package com.ubuniworks.dao.hibernate;

import com.ubuniworks.dao.LookupDao;
import com.ubuniworks.model.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of LookupDao.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Modified by jgarcia: updated to hibernate 4
 */
@Repository
public class LookupDaoHibernate implements LookupDao {
    private final SessionFactory sessionFactory;
    private Log log = LogFactory.getLog(LookupDaoHibernate.class);

    /**
     * Initialize LookupDaoHibernate with Hibernate SessionFactory.
     *
     * @param sessionFactory
     */
    @Autowired
    public LookupDaoHibernate(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("Retrieving all role names...");
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Role.class).list();
    }
}

package dao;

import config.JPAUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class DaoHibernate {

    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoHibernate(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }
}

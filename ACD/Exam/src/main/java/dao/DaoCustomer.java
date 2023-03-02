package dao;

import config.JPAUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.hibernate.Customer;

public class DaoCustomer {

    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoCustomer(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }


    //delete all the data of a customer, it there are orders, ask the user first
    public int delete(Customer customer){
        int response = 0;
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(customer));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            response = -1;
        } finally {
            em.close();
        }
        return response;
    }
}

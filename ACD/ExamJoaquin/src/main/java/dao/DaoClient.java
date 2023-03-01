package dao;

import config.JPAUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import modelo.hibernate.Client;

public class DaoClient {

    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoClient(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }




    //3. hibernate eliminar cliente y si tiene purchases preguntar al usuario
    public int delete(Client client) {
        int response = 1;
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(client));
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

    //get client



}

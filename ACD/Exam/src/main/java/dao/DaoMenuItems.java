package dao;

import config.JPAUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import model.hibernate.Customer;
import model.hibernate.MenuItem;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class DaoMenuItems {

    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoMenuItems(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }


    // Show all the menu items ordered by "John Doe" (anytime). Avoid duplications.
    //customer with only name and lastName
    //Customer cust = new Customer("John", "Doe");
    public List<MenuItem> getAll(Customer customer) {
        List<MenuItem> response;
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            response = em.createQuery(
                            "SELECT DISTINCT mi FROM MenuItem mi " +
                                    "JOIN OrderItems oi ON mi.id = oi.menuItem.id " +
                                    "JOIN Order o ON o.id = oi.order.id " +
                                    "JOIN Customer c ON c.id = o.customer.id " +
                                    "WHERE c.firstName = :firstName AND c.lastName = :lastName", MenuItem.class)
                    .setParameter("firstName", customer.getFirstName())
                    .setParameter("lastName", customer.getLastName())
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            response = Collections.emptyList();
        } finally {
            em.close();
        }

        return response;
    }


}

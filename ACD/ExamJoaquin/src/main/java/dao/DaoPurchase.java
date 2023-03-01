package dao;

import config.JPAUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import modelo.hibernate.Client;
import modelo.hibernate.Items;
import modelo.hibernate.Purchase;
import modelo.hibernate.PurchaseItem;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DaoPurchase {
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoPurchase(JPAUtil jpautil) {
        this.jpautil = jpautil;
    }

    //1. hibernate añadir un purchase de dos items al carro de anne
    public int add(Purchase purchase) {
        /*
        el purchase que se recibe debe contener los items que se van a comprar
        y el cliente que va a comprar
        por tanto seria algo asi:
        Purchase purchase = new Purchase();
        purchase.setClient(client);
        PurchaseItem purchaseItem1 = new PurchaseItem(1, 1, new Item(1, "item1", 1.0));
        PurchaseItem purchaseItem2 = new PurchaseItem(1, 1, new Item(2, "item2", 2.0));
        purchase.setPurchaseItems(List.of(purchaseItem1, purchaseItem2));
        purchase.setTotal(3.0);
        purchase.setPurchaseDate(new Date());

        con estos datos se crea el purchase y se añade a la base de datos
         */
        int response = 0;
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(purchase);
            for (PurchaseItem item : purchase.getPurchaseItems()) {
                em.merge(item);
            }
            tx.commit();
        } catch (Exception e) {
            log.error("Error adding purchase: {}", e.getMessage());
            tx.rollback();
            response = -1;
        } finally {
            em.close();
        }
        return response;
    }

    public Client getClient(int id) {
        Client client = null;
        em = jpautil.getEntityManager();
        try {
            client = em.find(Client.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }

    //2. hibernate getAll Sin repetir
    public List<Items> getAll(Client client) {
        List<Items> items = Collections.emptyList();
        em = jpautil.getEntityManager();
        try {
            items = em.createQuery("select pi.item from PurchaseItem pi where purchase.client.name =: name", Items.class)
                    .setParameter("name", client.getName())
                    .getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            em.close();
        }
        return items;
    }

    //get all de las purchases de un cliente
    public List<Purchase> getAllPurchases(Client client) {
        List<Purchase> purchases = Collections.emptyList();
        em = jpautil.getEntityManager();
        try {
            purchases = em.createQuery("select p from Purchase p where client.name =: name", Purchase.class)
                    .setParameter("name", client.getName())
                    .getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            em.close();
        }
        return purchases;
    }

    //5. mongo añadir item a una purchase

}

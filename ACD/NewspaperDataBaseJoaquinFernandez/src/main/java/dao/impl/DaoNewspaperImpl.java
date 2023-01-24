package dao.impl;

import dao.DaoNewspaper;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import model.Newspaper;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class DaoNewspaperImpl implements DaoNewspaper {


    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DaoNewspaperImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;

    }

    @Override
    public List<Newspaper> getAll() {
        List<Newspaper> response = null;
        em = jpaUtil.getEntityManager();
        try {
            response = em.createNamedQuery("GET_ALL_NEWSPAPERS", Newspaper.class)
                    .getResultList();
        } catch (PersistenceException ex){
            log.error(ex.getMessage());
        }

        return response;
    }





    @Override
    public Newspaper get(int id) {
        Newspaper response = null;
        em = jpaUtil.getEntityManager();
        try {
            response = em.find(Newspaper.class, id);

            if (response != null) {
                return response;
            }
        } catch (PersistenceException exception){
            log.error(exception.getMessage());
        }
        return response;
    }

    @Override
    public int add(Newspaper n) {
        em = jpaUtil.getEntityManager();
        int response = 0;
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(n);
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

    @Override
    public Map<String, Integer> getNbrArticles(int newspaper) {
        Map<String, Integer> map = null;
        em = jpaUtil.getEntityManager();
        try {
            map = em
                    .createQuery("select a.type.description as des, count(a) as number from Article a where a.newspaper.id =:newspaper group by a.type.description", Tuple.class)
                    .setParameter("newspaper", newspaper)
                    .getResultList()
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    tuple -> tuple.get("des").toString(),
                                    tuple -> Integer.parseInt(tuple.get("number").toString())
                            ));
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return map;
    }


    @Override
    public int delete(int id){
        // pasar newspaper desde seleccion de tabla
        int response;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Newspaper np = em.find(Newspaper.class, id);
            em.remove(em.merge(np));
            tx.commit();
            response = 1;
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            response = -1;
        } /*catch (PersistenceException)*/finally {
            if (em!=null) em.close();
        }
        return response;
    }



    @Override
    public int update(Newspaper n) {
        int response;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(n);
            tx.commit();
            response = 1;
        }
        catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            response = -1;
        }
        finally {
            if (em != null)  em.close();
        }
        return response;
    }


}

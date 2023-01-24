package dao.impl;

import dao.Const;
import dao.DaoReader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import model.*;

import java.util.List;

@Log4j2
public class DaoReaderImpl implements DaoReader {

    private JPAUtil jpaUtil;

    private EntityManager em;

    @Inject
    public DaoReaderImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<Integer, List<Reader>> getAll() {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            response = Either.right(em
                    .createNamedQuery("GET_ALL_READERS", Reader.class)
                    .getResultList());

        } catch (PersistenceException e) {
            e.printStackTrace();
            response = Either.left(-1);
        } finally {
            em.close();
        }
        return response;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(Newspaper idNewspaper) {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            response = Either.right(em
                    .createNamedQuery("GET_ALL_READERS_BY_NEWSPAPER", Reader.class)
                    .setParameter("idNewspaper", idNewspaper.getId())
                    .getResultList());
        } catch (PersistenceException e) {
            e.printStackTrace();
            response = Either.left(-1);
        } finally {
            em.close();
        }
        return response;
    }

    public Either<Integer, List<Reader>> getAll(ArticleType description) {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            response = Either.right(em
                    .createNamedQuery("GET_READER_BY_TYPE_DESC", Reader.class)
                    .setParameter("description", description.getDescription())
                    .getResultList());
        } catch (PersistenceException e) {
            e.printStackTrace();
            response = Either.left(-1);
        } finally {
            em.close();
        }
        return response;
    }

    @Override
    public Either<Integer, Reader> get(int id) {
        Either<Integer, Reader> response;
        em = jpaUtil.getEntityManager();
        try {
            Reader reader = em
                    .createQuery("from Reader where id= :id", Reader.class)
                    .setParameter("id", id)
                    .getSingleResult();

            response = Either.right(reader);

        } catch (Exception e) {
            e.printStackTrace();
            response = Either.left(0);

        } finally {
            em.close();
        }
        return response;
    }

    //Delete a Reader, if they have active subscriptions ask the user first. Delete their login in
    //cascade, and the not active subscriptions, if any (Not cascade).
    @Override
    public int delete(int id) {
        int result;
        em = jpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Reader reader = em.find(Reader.class, id);

            List<Subscription> subscriptions = em.createNamedQuery(
                            "GET_ALL_SUBSCRIPTIONS_READER", Subscription.class)
                    .setParameter("idReader", reader.getId())
                    .getResultList();
            List<ReadArticle> readArticles = em.createNamedQuery(
                            "GET_ALL_READ_ARTICLES_READER", ReadArticle.class)
                    .setParameter("idReader", reader.getId())
                    .getResultList();
            if (!subscriptions.isEmpty()) {
                em.createNamedQuery(
                                "DELETE_SUBSCRIPTION_READER")
                        .setParameter("idReader", id).executeUpdate();
            }
            if (!readArticles.isEmpty()) {
                em.createNamedQuery(
                                "DELETE_READ_ARTICLES_READER")
                        .setParameter("idReader", id).executeUpdate();
            }
            em.remove(em.merge(reader));
            transaction.commit();
            result = 1;

        } catch (
                Exception e) {
            e.printStackTrace();
            transaction.rollback();
            log.error(e.getMessage(), e);
            result = 0;
        } finally {
            em.close();
        }
        return result;
        //todo screen
    }


}

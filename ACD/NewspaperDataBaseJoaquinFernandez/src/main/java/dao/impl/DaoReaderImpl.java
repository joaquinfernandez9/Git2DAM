package dao.impl;

import dao.Const;
import dao.DaoReader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.Reader;
import model.Subscription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReaderImpl implements DaoReader {

    private JPAUtil jpaUtil;

    private EntityManager em;

    @Inject
    public DaoReaderImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<Integer, List<Reader>> getAll(int idNews, String description) {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            if (idNews!=0){
                response = Either.right(em
                        .createNamedQuery("GET_ALL_READERS_BY_NEWSPAPER", Reader.class)
                        .setParameter("idNewspaper", idNews)
                        .getResultList());
            }/*else if (description!=null) {
                response = Either.right(em
                        .createNamedQuery("GET_ALL_READERS_BY_DESCRIPTION", Reader.class)
                        .setParameter("description", description)
                        .getResultList());
            } */else {
                response = Either.right(em
                        .createNamedQuery("GET_ALL_READERS", Reader.class)
                        .getResultList());
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            response = Either.left(-1);
        } finally {
            em.close();
        }
        return response;
    }

    //Get all the readers subscribed to a specific newspaper. Make sure that the subscription is
    //not cancelled (null). (getAll(Newspaper) in readersDao, show in Readers List panel)
    public Either<Integer, List<Reader>> getAll(int idNewspaper) {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            response = Either.right(em
                    .createNamedQuery("GET_ALL_READERS_BY_NEWSPAPER", Reader.class)
                    .setParameter("idNewspaper", idNewspaper)
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
            if (!subscriptions.isEmpty()){
                em.createNamedQuery(
                                "DELETE_SUBSCRIPTION_READER")
                        .setParameter("idReader", id).executeUpdate();
                em.remove(em.merge(reader));
                transaction.commit();
                result = 1;
            } else {
                em.remove(em.merge(reader));
                transaction.commit();
                result = 1;
            }
        } catch (Exception e) {
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


    public Either<Integer, List<Reader>> getAll(String description) {
        Either<Integer, List<Reader>> response;
        em = jpaUtil.getEntityManager();
        try {
            response = Either.right(em
                    .createNamedQuery("GET_READER_BY_TYPE_DESC", Reader.class)
                    .setParameter("description", description)
                    .getResultList());
        } catch (PersistenceException e) {
            e.printStackTrace();
            response = Either.left(-1);
        } finally {
            em.close();
        }
        return response;
    }


}

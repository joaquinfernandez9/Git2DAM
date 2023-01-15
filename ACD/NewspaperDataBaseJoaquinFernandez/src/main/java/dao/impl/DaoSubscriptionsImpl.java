package dao.impl;

import dao.Const;
import dao.DaoSubscriptions;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import model.Subscription;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoSubscriptionsImpl implements DaoSubscriptions {


    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DaoSubscriptionsImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;

    }



    @Override
    public int delete(int idReader, int idNewspaper) {
        int response;
        response = 1;
        return response;
    }

    public int add(Subscription subscription) {
        int response;
        response = 1;
        return response;
    }

    @Override
    public Either<Object, List<Subscription>> getAll() {
        Either<Object, List<Subscription>> result = null;

        return result;
    }

    @Override
    public Either<Object, List<Subscription>> getAll(int id) {
        Either<Object, List<Subscription>> result;
        em = jpaUtil.getEntityManager();
        try {
            result = Either.right(em
                            .createNamedQuery("GET_ALL_SUBSCRIPTIONS_READER", Subscription.class)
                    .setParameter("idReader", id)
                    .getResultList());
        } catch (Exception e) {
            e.printStackTrace();
            result = Either.left(-1);
        } finally {
            em.close();
        }
        return result;

    }



}

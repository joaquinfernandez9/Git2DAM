package dao.impl;

import dao.Const;
import dao.DaoReadArticle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import model.ReadArticle;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Log4j2
public class DaoReadArticleImpl implements DaoReadArticle {


    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DaoReadArticleImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<Integer, List<ReadArticle>> getAll() {
        Either<Integer, List<ReadArticle>> result;
        result = Either.right(Collections.emptyList());
        return result;
    }


    //d.
    @Override
    public int add(ReadArticle readArticle){
        int response=0;
        em = jpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(readArticle);
            em.getTransaction().commit();

            response = 1;
        } catch (PersistenceException ex){
            log.error(ex.getMessage());
            response = -1;
        }

        return response;
    }

    @Override
    public Map<Double, String> getAvgRating(int reader) {
        Map<Double, String> result;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            result = em
                    .createQuery("select avg(r.ranking) as avg, r.article.newspaper as title from ReadArticle r where r.reader.id = :idReader group by r.article.newspaper", Tuple.class)
                    .setParameter("idReader", reader)
                    .getResultList()
                    .stream()
                    .collect(
                            Collectors.toMap(
                                    tuple -> Double.valueOf(tuple.get("avg").toString()),
                                    tuple -> tuple.get("title").toString()
                            ));
            tx.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            result = null;
        } finally {
            em.close();
        }
        return result;
    }



}

package dao.impl;

import dao.Const;
import dao.DaoReadArticle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import model.ReadArticle;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        }

        return response;
    }

    @Override
    public int delete(int id){
        int response=0;
        return response;
    }




}

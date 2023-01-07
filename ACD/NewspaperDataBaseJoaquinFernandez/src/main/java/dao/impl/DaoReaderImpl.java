package dao.impl;

import dao.Const;
import dao.DaoReader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.Reader;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
        Either<Integer, List<Reader>> response = null;
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




}

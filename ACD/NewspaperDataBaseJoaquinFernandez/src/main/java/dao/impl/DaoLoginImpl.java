package dao.impl;

import dao.Const;
import dao.DaoLogin;
import dao.DaoReader;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class DaoLoginImpl implements DaoLogin {

    private final DaoReader daoReader;

    private final JPAUtil jpaUtil;

    private EntityManager em;

    @Inject
    public DaoLoginImpl(DaoReader daoReader, JPAUtil jpaUtil) {
        this.daoReader = daoReader;
        this.jpaUtil = jpaUtil;
    }

    @Override
    public int login(String user, String password) {
        int response;
        em = jpaUtil.getEntityManager();
        try {
            Login login = em
                    .createQuery("from Login where user= :user and password= :password", Login.class)
                    .setParameter("user", user)
                    .setParameter("password", password)
                    .getSingleResult();

            response = login.getReader().getId();

        } catch (Exception e) {
            e.printStackTrace();
            response = 0;

        } finally {
            em.close();
        }

        return response;
    }


    @Override
    public int delete(int id) {
        int result;
        em = jpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Reader reader = em.find(Reader.class, id);
            em.remove(em.merge(reader));
            transaction.commit();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            log.error(e.getMessage(), e);
            result = 0;
        } finally {
            em.close();
        }
        return result;
    }


    //UPDATE READER
    @Override
    public int update(Login login) {
        Login login1 = daoReader.get(login.getReader().getId()).get().getLogin();
        int result;
        em = jpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        if (login1 != null) {
            try {
                if (login.getPassword().isBlank()) {
                    login.setPassword(login1.getPassword());
                }
                if (login.getUser().isBlank()) {
                    login.setUser(login1.getUser());
                }
                if (login.getReader().getName_reader().isBlank()) {
                    login.getReader().setName_reader(login1.getReader().getName_reader());
                }
                if (login.getReader().getBirth_reader() == null) {
                    login.getReader().setBirth_reader(login1.getReader().getBirth_reader());
                }
                em.merge(login.getReader());
                transaction.commit();
                result = 1;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
                log.error(e.getMessage(), e);
                result = 0;
            } finally {
                em.close();
            }
        } else {
            result = -2;
        }
        return result;
    }


    //ADD READER
    @Override
    public int add(Login login) {
        int response;
        em = jpaUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(login);
            transaction.commit();
            response = 1;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
            response = -1;
            log.error(e.getMessage(), e);
        } finally {
            em.close();
        }
        return response;
    }

}

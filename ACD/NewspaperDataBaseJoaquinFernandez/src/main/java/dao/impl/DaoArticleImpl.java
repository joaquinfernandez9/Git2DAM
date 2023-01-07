package dao.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.Const;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Article;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoArticleImpl implements DaoArticle {

    private JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoArticleImpl(JPAUtil jpaUtil) {
        this.jpautil = jpaUtil;
    }

    //??
    @Override
    public List<Article> getAll() {
        List<Article> response = null;
        return response;
    }

    //getAll articles de X newspaper
    @Override
    public List<Article> getAll(int newspaperId) {

        List<Article> list;
        try {
            em = jpautil.getEntityManager();
            list = em.createQuery("from Article where newspaper.id = :id", Article.class)
                    .setParameter("id", newspaperId)
                    .getResultList();
        }
        finally {
            if (em != null)  em.close();
        }

        return list;
    }

    //get

    @Override
    public int save(Article n) {
        return 1;
    }

    @Override
    public int delete(int id) {
        int response = 1;
        return response;
    }

}

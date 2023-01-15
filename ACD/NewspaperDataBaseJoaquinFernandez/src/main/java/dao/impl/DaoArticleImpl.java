package dao.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.Const;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import model.Article;
import lombok.extern.log4j.Log4j2;
import model.Newspaper;
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
        List<Article> response = Collections.emptyList();
        em = jpautil.getEntityManager();
        try {
            response = em
                    .createQuery("from Article", Article.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return response;
    }


    //get all articles of a newspaper
    public List<Article> getAll(int idNewspaper) {
        List<Article> response = Collections.emptyList();
        em = jpautil.getEntityManager();
        try {
            response = em
                    .createQuery("from Article where newspaper.id = ?", Article.class)
                    .setParameter(1, idNewspaper)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return response;
    }


    @Override
    public int save(Article n) {
        return 1;
    }

    @Override
    public int delete(int id) {
        int response = 1;
        return response;
    }

    @Override
    public int update(Article a) {
        int response = 1;
        return response;
    }

}

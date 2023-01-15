package dao.impl;

import dao.Const;
import dao.DaoNewspaper;
import dao.DaoType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import model.ArticleType;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoTypeImpl implements DaoType {

    private JPAUtil jpaUtil;
    private EntityManager em;
    @Inject
    public DaoTypeImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public List<ArticleType> getAll() {
        List<ArticleType> response= Collections.emptyList();
        em = jpaUtil.getEntityManager();
        try {
            response = em.createQuery("select at from ArticleType at", ArticleType.class)
                    .getResultList();
        } catch (PersistenceException ex){
            log.error(ex.getMessage());
        }

        return response;
    }

    @Override
    public ArticleType get(Integer id) {
        List<ArticleType> n=null;

        return (ArticleType) n;
    }

}

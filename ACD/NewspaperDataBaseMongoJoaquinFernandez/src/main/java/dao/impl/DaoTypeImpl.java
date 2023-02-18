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

    @Inject
    public DaoTypeImpl() {

    }

    @Override
    public List<ArticleType> getAll() {
        return null;
    }

    @Override
    public ArticleType get() {
        return null;
    }

}

package dao.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
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


    private final DataBaseConnectionPool db;

    @Inject
    public DaoArticleImpl(DataBaseConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Article> getAll() {
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query("select * from article",
                BeanPropertyRowMapper.newInstance(Article.class));
        return response;
    }

    @Override
    public List<Article> getAll(int idType) {
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query("select * from article where id_type=?",
                BeanPropertyRowMapper.newInstance(Article.class), idType);
        return response;
    }

    @Override
    public Article get(int id){
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query("select * from article where id=?",
                BeanPropertyRowMapper.newInstance(Article.class), id);
        return response.get(0);

    }


    @Override
    public int save(Article a) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                db.getDataSource()).withTableName("article");
        Map<String, Object> param = new HashMap<>();
        param.put("name_article", a.getTitle());
        param.put("id_type", a.getTypeID());
        param.put("id_newspaper", a.getNewspaperID());

        a.setArticleID((int) jdbcInsert.executeAndReturnKey(param).longValue());
        return jdbcInsert.execute(param);


    }

    @Override
    public int delete(int id) {
        int response;
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            response = jtm.update("delete from article where id=?", id);
        }catch (DataIntegrityViolationException e) {
            if (Objects.requireNonNull(e.getMessage()).contains("IntegrityConstraintViolation")) {
                response = -2;
            } else {
                response = -3;
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoNewspaper.class.getName())
                    .log(Level.SEVERE, null, ex);
            response = -4;
        }
        return response;
    }

    @Override public int update(Article a){
        JdbcTemplate jdbcTemplate =  new JdbcTemplate(db.getDataSource());
        return jdbcTemplate.update("update article set name_article=? where id=?",
                a.getTitle(), a.getArticleID());
    }


}

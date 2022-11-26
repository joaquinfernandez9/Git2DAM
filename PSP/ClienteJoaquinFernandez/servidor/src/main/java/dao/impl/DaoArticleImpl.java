package dao.impl;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import dao.Const;
import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.dataBase.DataBaseConnectionPool;
import model.Article;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        response = jtm.query(Const.SELECT_FROM_ARTICLE,
                BeanPropertyRowMapper.newInstance(Article.class));
        return response;
    }

    public List<Integer> getAllByNewspaper(int id) {
        List<Integer> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        //no such method exception
        response = jtm.query("select count(*) as 'num' from article where id_newspaper = ?",
                new IntegerRowMapper(), id);
        return response;
    }

    @Override
    public List<Article> getArticlesOfAReader(int idReader) {
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query(Const.getArticlesOfReader,
                BeanPropertyRowMapper.newInstance(Article.class), idReader, idReader);
        return response;
    }

    //1b2 Show articles by type
    @Override
    public List<Article> getAll(int idType) {
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query(Const.getAllArticlesByType,
                BeanPropertyRowMapper.newInstance(Article.class), idType);
        return response;
    }

    @Override
    public Article get(int id) {
        List<Article> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query(Const.getArticle,
                BeanPropertyRowMapper.newInstance(Article.class), id);
        //check if article null
        return response.get(0);

    }


    @Override
    public int save(Article a) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                db.getDataSource()).withTableName(Const.ARTICLE)
                .usingGeneratedKeyColumns(Const.ID);
        Map<String, Object> param = new HashMap<>();
        param.put(Const.NAME_ARTICLE, a.getName_article());
        param.put(Const.ID_TYPE, a.getId_type());
        param.put(Const.ID_NEWSPAPER, a.getId_newspaper());
        //if we keep this, it adds the article twice
        //a.setArticleID((int) jdbcInsert.executeAndReturnKey(param).longValue());
        return jdbcInsert.execute(param);
    }

    @Override
    public int delete(int id) {
        int response;
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            response = jtm.update(Const.deleteArticle, id);
        } catch (DataIntegrityViolationException e) {
            if (Objects.requireNonNull(e.getMessage())
                    .contains(Const.INTEGRITY_CONSTRAINT_VIOLATION)) {
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

    @Override
    public int update(Article a) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(db.getDataSource());
        return jdbcTemplate.update(Const.updateArticle,
                a.getName_article(), a.getId());
    }


}

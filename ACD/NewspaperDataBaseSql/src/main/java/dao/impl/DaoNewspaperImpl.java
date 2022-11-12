package dao.impl;

import dao.DaoNewspaper;
import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
import model.Newspaper;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Log4j2
public class DaoNewspaperImpl implements DaoNewspaper {


    private final DataBaseConnectionPool db;

    @Inject
    public DaoNewspaperImpl(DataBaseConnectionPool db) {
        this.db = db;
    }

    @Override
    public List<Newspaper> getAll() {
        List<Newspaper> response;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        response = jtm.query("select * from newspaper",
                BeanPropertyRowMapper.newInstance(Newspaper.class));
        return response;
    }

    @Override
    public Newspaper get(int id) {
        List<Newspaper> n;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        n = jtm.query("select * from newspaper where id=?",
                BeanPropertyRowMapper.newInstance(Newspaper.class), id);
        return n.isEmpty() ? null : n.get(0);
    }

    @Override
    public int add(Newspaper n) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                db.getDataSource()).withTableName("newspaper").usingGeneratedKeyColumns("id");
        Map<String, Object> param = new HashMap<>();
        param.put("name_newspaper", n.getName_newspaper());
        param.put("release_date", n.getRelease_date());
        return jdbcInsert.execute(param);
    }


    @Override
    public int delete(int id) {
        int response;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(db.getDataSource());
        org.springframework.transaction.TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());

            jtm.update("delete from readarticle where id_article in (select id from article where id_newspaper = ?)", id);

            jtm.update("delete from article where id_newspaper=?", id);

            jtm.update("delete from subscribe where id_newspaper=?", id);

            response = jtm.update("delete from newspaper where id=?", id);

            txManager.commit(txStatus);
        } catch (DataIntegrityViolationException e) {
            txManager.rollback(txStatus);
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

//    private


    @Override
    public int update(Newspaper n) {
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        return jtm.update("update newspaper set name_newspaper=?, " +
                        "release_date=? where id=?",
                n.getName_newspaper(), n.getRelease_date(), n.getId());
    }


}

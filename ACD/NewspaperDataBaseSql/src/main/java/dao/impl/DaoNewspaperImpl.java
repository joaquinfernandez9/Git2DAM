package dao.impl;

import dao.DaoNewspaper;
import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
import model.Newspaper;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
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
        return  n.isEmpty() ? null : n.get(0);
    }

    @Override
    public int add(Newspaper n) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                db.getDataSource()).withTableName("newspaper");
        Map<String, Object> param = new HashMap<>();
        param.put("name_newspaper", n.getNewspaperName());
        param.put("release_date", n.getReleaseDate());
        //no se si esto ultimo hace falta
        n.setNewspaperID((int) jdbcInsert.executeAndReturnKey(param)
                .longValue());
        return jdbcInsert.execute(param);
    }


    @Override
    public int delete(int id) {
        int response;
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            response = jtm.update("delete from newspaper where id=?", id);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("IntegrityConstraintViolation")) {
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

    //delete with articles
    @Override
    public int deleteWithArticles(int id) {
        int response;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new
                DataSourceTransactionManager(db.getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            jtm.update("delete from articles where id_newspaper=?", id);
            response = jtm.update("delete from newspaper where id=?", id);
        } catch (Exception e){
            transactionManager.rollback(txStatus);
            log.error(e.getMessage());
            response = -3;
        }
        return response;
    }

    @Override
    public int update(Newspaper n) {
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        return jtm.update("update newpaper set name_newspaper=?, " +
                        "release_date=? where id=?",
                n.getNewspaperName(), n.getReleaseDate(), n.getNewspaperID());
    }


}

package dao.impl;

import dao.Const;
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
        response = jtm.query(Const.getAllNewspaper, BeanPropertyRowMapper.newInstance(Newspaper.class));
        return response;
    }

    @Override
    public Newspaper get(int id) {
        List<Newspaper> n;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        n = jtm.query(Const.getNewspaper,
                BeanPropertyRowMapper.newInstance(Newspaper.class), id);
        if (n.isEmpty()) {
            return null;
        } else {
            return n.get(0);
        }
    }

    @Override
    public int add(Newspaper n) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                db.getDataSource()).withTableName(Const.NEWSPAPER)
                .usingGeneratedKeyColumns(Const.ID);
        Map<String, Object> param = new HashMap<>();

        param.put(Const.NAME_NEWSPAPER, n.getName_newspaper());
        param.put(Const.RELEASE_DATE, n.getRelease_date());
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

            jtm.update(Const.deleteFromReadArticle, id);

            jtm.update(Const.DeleteFromArticle, id);

            jtm.update(Const.deleteFromSubscribe, id);

            response = jtm.update(Const.DELETE_FROM_NEWSPAPER_WHERE_ID, id);

            txManager.commit(txStatus);
        } catch (DataIntegrityViolationException e) {
            try {
                txManager.rollback(txStatus);
                response = 1;
            } catch (Exception ex) {
                log.error(Const.ERROR_IN_ROLLBACK, ex);
                response = -2;
            }
            if (Objects.requireNonNull(e.getMessage()).contains(Const.INTEGRITY_CONSTRAINT_VIOLATION)) {
                response = -6;
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
        return jtm.update(Const.updateNewspaper,
                n.getName_newspaper(), n.getRelease_date(), n.getId());
    }


}

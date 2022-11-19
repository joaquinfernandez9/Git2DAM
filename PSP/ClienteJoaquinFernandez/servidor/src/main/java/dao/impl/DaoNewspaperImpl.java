package dao.impl;

import dao.DaoNewspaper;
import domain.modelo.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import dao.Const;
import dao.dataBase.DataBaseConnectionPool;
import model.Newspaper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        response = jtm.query(Const.getAllNewspaper,
                BeanPropertyRowMapper.newInstance(Newspaper.class));
        if (response.isEmpty()) {
            throw new NotFoundException("No newspaper found");
        } else {
            return response;
        }

    }

    @Override
    public Newspaper get(int id) {
        List<Newspaper> n;
        JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
        n = jtm.query(Const.getNewspaper,
                BeanPropertyRowMapper.newInstance(Newspaper.class), id);
        if (n.isEmpty()) {
            throw new NotFoundException("No newspaper found");
        } else {
            return n.get(0);
        }
    }

    @Override
    public Newspaper add(Newspaper n) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                    db.getDataSource()).withTableName(Const.NEWSPAPER)
                    .usingGeneratedKeyColumns(Const.ID);
            Map<String, Object> param = new HashMap<>();
            List<Newspaper> response;
            param.put(Const.NAME_NEWSPAPER, n.getName_newspaper());
            param.put(Const.RELEASE_DATE, n.getRelease_date());
            jdbcInsert.execute(param);
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            response = jtm.query(Const.getNewspaper,
                    BeanPropertyRowMapper.newInstance(Newspaper.class), n.getId());
            return response.get(0);
        } catch (IndexOutOfBoundsException e){
            //si falla el get salta esta excepcion, por lo que entendemos que no se añade
            throw new DatabaseException("Newspaper not added correctly");
        }
    }

    @Override
    public void delete(int id) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager txManager =
                new DataSourceTransactionManager(db.getDataSource());
        TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());

            jtm.update(Const.deleteFromReadArticle, id);

            jtm.update(Const.DeleteFromArticle, id);

            jtm.update(Const.deleteFromSubscribe, id);

            jtm.update(Const.DELETE_FROM_NEWSPAPER_WHERE_ID, id);

            txManager.commit(txStatus);
        } catch (DataIntegrityViolationException e) {
            try {
                txManager.rollback(txStatus);
                throw new DataIntegrityViolationException("Newspaper is used");
            } catch (Exception ex) {
                log.error(Const.ERROR_IN_ROLLBACK, ex);
                throw new DatabaseRollbackException("Rollback Failed");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new CommonException(ex.getMessage());
        }
    }

    @Override
    public Newspaper update(Newspaper n) {
        try {
            List<Newspaper> response;
            JdbcTemplate jtm = new JdbcTemplate(db.getDataSource());
            jtm.update(Const.updateNewspaper,
                    BeanPropertyRowMapper.newInstance(Newspaper.class),
                    n.getName_newspaper(), n.getRelease_date(), n.getId());

            response = jtm.query(Const.getNewspaper,
                    BeanPropertyRowMapper.newInstance(Newspaper.class), n.getId());
            return response.get(0);
        } catch (DataAccessException e){
            throw new DatabaseIntegrityViolation("Can't access data, nothing updated");
        }
    }


}

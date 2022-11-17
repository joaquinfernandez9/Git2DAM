package dao.impl;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import dao.Const;
import dao.DaoNewspaper;
import dao.DaoType;
import dao.dataBase.DataBaseConnectionPool;
import model.ArticleType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoTypeImpl implements DaoType {



    private final DataBaseConnectionPool pool;

    @Inject
    public DaoTypeImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<ArticleType> getAll() {
        List<ArticleType> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        response = jtm.query(Const.getAllTypes,
                BeanPropertyRowMapper.newInstance(ArticleType.class));
        return response;
    }

    @Override
    public ArticleType get(int id) {
        List<ArticleType> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        response = jtm.query(Const.getType,
                BeanPropertyRowMapper.newInstance(ArticleType.class), id);
        return response.get(0);
    }

    @Override
    public int add(ArticleType a) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                pool.getDataSource()).withTableName(Const.TYPE);
        Map<String, Object> param = new HashMap<>();
        param.put(Const.DESCRIPTION, a.getDescription());
        //a.setId((int) jdbcInsert.executeAndReturnKey(param).longValue());
        return jdbcInsert.execute(param);
    }

    @Override
    public int delete(int id) {
        int response;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            response = jtm.update(Const.DELETE_FROM_TYPE_WHERE_ID, id);
        } catch (DataIntegrityViolationException e) {
            e.getMessage().contains(Const.INTEGRITY_CONSTRAINT_VIOLATION);
            response = -6;
        } catch (Exception ex) {
            Logger.getLogger(DaoNewspaper.class.getName())
                    .log(Level.SEVERE, null, ex);
            response = -2;
        }
        return response;
    }

    @Override
    public int update(ArticleType type) {
        JdbcTemplate template = new JdbcTemplate(pool.getDataSource());
        return template.update(Const.updateType,
                type.getDescription(), type.getId());
    }

}

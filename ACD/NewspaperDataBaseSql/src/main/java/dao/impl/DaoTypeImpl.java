package dao.impl;

import dao.DaoNewspaper;
import dao.DaoType;
import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
import model.ArticleType;
import lombok.extern.log4j.Log4j2;
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

    private DataBaseConnectionPool pool;

    @Inject
    public DaoTypeImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public List<ArticleType> getAll() {
        List<ArticleType> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        response = jtm.query("select * from type",
                BeanPropertyRowMapper.newInstance(ArticleType.class));
        return response;
    }

    @Override
    public ArticleType get(int id) {
        List<ArticleType> response;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        response = jtm.query("select * from type where id=?",
                BeanPropertyRowMapper.newInstance(ArticleType.class), id);
        return response.get(0);
    }

    @Override
    public int add(ArticleType a){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                pool.getDataSource()).withTableName("type");
        Map<String, Object> param = new HashMap<>();
        param.put("description", a.getDescription());
        //no se si esto ultimo hace falta
//        a.setId((int) jdbcInsert.executeAndReturnKey(param)
//                .longValue());
        //returns the number of affected rows
        return jdbcInsert.execute(param);
    }

    @Override
    public int delete(int id){
        int response;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            response = jtm.update("delete from type where id=?", id);
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

    @Override
    public int update(ArticleType type){
        JdbcTemplate template = new JdbcTemplate(pool.getDataSource());
        return template.update("update type set description=? where id=?",
                type.getDescription(), type.getId());
    }

}

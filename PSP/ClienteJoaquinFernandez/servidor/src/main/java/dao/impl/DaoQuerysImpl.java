package dao.impl;

import dao.DaoQuerys;
import domain.modelo.DatabaseException;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.querys.QueryArticleRating;
import model.querys.QueryDescNumber;
import dao.Const;
import dao.dataBase.DataBaseConnectionPool;
import model.Reader;
import model.querys.QueryArticlesNewspaper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoQuerysImpl implements DaoQuerys {


    private final DataBaseConnectionPool pool;

    @Inject
    public DaoQuerysImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }


    //Get the description and the number of readers of each article
    @Override
    public List<QueryDescNumber> getAll() {
        List<QueryDescNumber> response;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(Const.firstQuery)) {
            ResultSet rs = ps.executeQuery();
            response = readRS(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return response;
    }

    private List<QueryDescNumber> readRS(ResultSet rs) {
        List<QueryDescNumber> response = new ArrayList<>();
        try {
            while (rs.next()) {
                String articleName = rs.getString(Const.ARTICLE_NAME);
                String description = rs.getString(Const.DESCRIPTION);
                int lectors = rs.getInt(Const.COUNT_READARTICLE_ID_READER);
                QueryDescNumber queryDescNumber = new QueryDescNumber(articleName, description, lectors);
                response.add(queryDescNumber);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }

    //Get the name of the 100 oldest subscriptors of newspaper
    @Override
    public List<Reader> getOldest(int idNewspaper) {
        List<Reader> response;
        try (Connection con = pool.getConnection();
             PreparedStatement ps = con.prepareStatement(Const.secondQuery)) {
            ps.setInt(1, idNewspaper);
            ResultSet rs = ps.executeQuery();
            response = readRSReader(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
        return response;
    }


    private List<Reader> readRSReader(ResultSet rs) {
        List<Reader> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(Const.ID);
                String name = rs.getString(Const.NAME_READER);
                LocalDate date = rs.getDate(Const.BIRTH_READER)
                        .toLocalDate();
                Reader r = new Reader(id, name, date);
                response.add(r);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


//    Get the articles of a given type, together with the name of the newspaper
    @Override
    public List<QueryArticlesNewspaper> getAll(String description) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        return jtm.query(Const.thirdQuery, (rs, rowNum) -> {
            QueryArticlesNewspaper queryArticlesNewspaper = new QueryArticlesNewspaper();
            queryArticlesNewspaper.setName_article(rs.getString(Const.ARTICLE_NAME_ARTICLE));
            queryArticlesNewspaper.setName_newspaper(rs.getString(Const.NEWSPAPER_NAME_NEWSPAPER));
            return queryArticlesNewspaper;
        }, description);
    }

    //Get the articles with a rating lower than 5 of a given newspaper,
    //indicating if the reader has
    //rated more than 4 articles with a lower-than-5 rating
    @Override
    public List<QueryArticleRating> getArticles(int idNewspaper) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<QueryArticleRating> response;
        response = jtm.query(Const.fourthQuery,
                BeanPropertyRowMapper.newInstance(QueryArticleRating.class), idNewspaper);
        return response;
    }

}

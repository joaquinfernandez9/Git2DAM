package dao.impl;

import dao.DaoQuerys;
import dao.dataBase.DataBaseConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Reader;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;
import model.querys.QueryDescNumber;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
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
    public Either<Integer, List<QueryDescNumber>> getAll() {
        Either<Integer, List<QueryDescNumber>> response = null;
        try (Connection con = pool.getConnection(); PreparedStatement ps = con.prepareStatement("select type.description, " + "count(readarticle.id_reader), " + "article.name_article\n" + "from type,\n" + "     article,\n" + "     readarticle\n" + "where type.id = article.id_type\n" + "  and article.id = readarticle.id_article\n" + "group by type.description, article.name_article;")) {
            ResultSet rs = ps.executeQuery();
            response = Either.right(readRS(rs));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }

    private List<QueryDescNumber> readRS(ResultSet rs) {
        List<QueryDescNumber> response = new ArrayList<>();
        try {
            while (rs.next()) {
                String articleName = rs.getString("article_name");
                String description = rs.getString("description");
                int lectors = rs.getInt("count(readarticle.id_reader)");
                QueryDescNumber queryDescNumber = new QueryDescNumber(articleName, description, lectors);
                response.add(queryDescNumber);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }

    //Get information about the readers subscribed to a specific newspaper
    @Override
    public Either<Integer, List<Reader>> getReadersSubscribed(int idNewspaper) {
        Either<Integer, List<Reader>> response;
        try (Connection con = pool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select reader.id, name_reader, birth_reader\n" +
                    "from reader,newspaper,subscribe\n" +
                    "where reader.id = subscribe.id_reader\n" +
                    "and newspaper.id = subscribe.id_newspaper\n" +
                    "and newspaper.id = ?" +
                    "and id_reader> 0");
            ps.setInt(1, idNewspaper);
            ResultSet rs = ps.executeQuery();
            response = Either.right(readRSReader(rs));
        } catch (SQLException e) {
            log.error(e.getMessage());
            response = Either.left(-3);
        }
        return response;
    }

    //Get the name of the 100 oldest subscriptors of newspaper
    @Override
    public Either<Integer, List<Reader>> getOldest(int idNewspaper) {
        Either<Integer, List<Reader>> response;
        try (Connection con = pool.getConnection();
        ) {
            PreparedStatement ps = con.prepareStatement("select name_reader\n" +
                    "from reader,\n" +
                    "     subscribe,\n" +
                    "     newspaper\n" +
                    "where newspaper.id = subscribe.id_newspaper\n" +
                    "  and subscribe.id_reader = reader.id\n" +
                    "  and newspaper.id = ?\n" +
                    "and subscribe.cancellation_date is null\n" +
                    "and subscribe.sing_date in\n" +
                    "    (select min(subscribe.sing_date) from subscribe)\n" +
                    "and id_reader > 0" +
                    "LIMIT 5");
            ps.setInt(1, idNewspaper);
            ResultSet rs = ps.executeQuery();
            response = Either.right(readRSReader(rs));
        } catch (SQLException e) {
            log.error(e.getMessage());
            response = Either.left(-3);
        }
        return response;
    }


    private List<Reader> readRSReader(ResultSet rs) {
        List<Reader> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_reader");
                LocalDate date = rs.getDate("birth_reader")
                        .toLocalDate();
                Reader r = new Reader(id, name, date);
                response.add(r);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


    //spring momento
//    Get the articles of a given type, together with the name of the newspaper
    @Override
    public List<QueryArticlesNewspaper> getAll(String description) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        return jtm.query("select article.name_article, newspaper.name_newspaper\n" + "from article, newspaper, type\n" + "where type.description = ?\n" + "and type.id = article.id_type\n" + "and article.id_newspaper = newspaper.id\n", (rs, rowNum) -> {
            QueryArticlesNewspaper queryArticlesNewspaper = new QueryArticlesNewspaper();
            queryArticlesNewspaper.setArticleName(rs.getString("article.name_article"));
            queryArticlesNewspaper.setNewspaperName(rs.getString("newspaper.name_newspaper"));
            return queryArticlesNewspaper;
        }, description);
    }

    //Get the articles with a rating lower than 5 of a given newspaper,
    //indicating if the reader has
    //rated more than 4 articles with a lower-than-5 rating
    @Override
    public List<QueryArticleRating> getArticles(String newspaperName) {
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<QueryArticleRating> response;
        response = jtm.query("SELECT article.name_article" +
                "FROM article,\n" +
                "newspaper,\n" +
                "readarticle\n" +
                "WHERE readarticle.ranking >= 5\n" +
                "and article.id_newspaper = newspaper.id\n" +
                "and newspaper.name_newspaper = ?\n" +
                "GROUP BY article.name_article\n" +
                "HAVING count(readarticle.id_reader) > 4",
                BeanPropertyRowMapper.newInstance(QueryArticleRating.class), newspaperName);
        return response;
    }

}

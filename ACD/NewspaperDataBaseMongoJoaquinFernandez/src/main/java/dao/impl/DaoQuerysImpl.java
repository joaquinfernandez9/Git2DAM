package dao.impl;

import dao.Const;
import dao.DaoQuerys;
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

import static java.util.Collections.emptyList;

@Log4j2
public class DaoQuerysImpl implements DaoQuerys {


    @Inject
    public DaoQuerysImpl() {
    }

    //Get the description and the number of readers of each article
    @Override
    public Either<Integer, List<QueryDescNumber>> getAll() {

        return null;
    }

    private List<QueryDescNumber> readRS(ResultSet rs) {

        return emptyList();
    }

    //Get the name of the 100 oldest subscriptors of newspaper
    @Override
    public Either<Integer, List<Reader>> getOldest(int idNewspaper) {

        return null;
    }


    private List<Reader> readRSReader(ResultSet rs) {

        return emptyList();
    }


//    Get the articles of a given type, together with the name of the newspaper
    @Override
    public List<QueryArticlesNewspaper> getAll(String description) {
        return emptyList();
    }

    //Get the articles with a rating lower than 5 of a given newspaper,
    //indicating if the reader has
    //rated more than 4 articles with a lower-than-5 rating
    @Override
    public List<QueryArticleRating> getArticles(int idNewspaper) {
       return emptyList();
    }

}

package dao.impl;

import dao.Const;
import dao.DaoReadArticle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import model.ReadArticle;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Log4j2
public class DaoReadArticleImpl implements DaoReadArticle {



    @Inject
    public DaoReadArticleImpl() {

    }

    @Override
    public Either<Integer, List<ReadArticle>> getAll() {
        Either<Integer, List<ReadArticle>> result;
        result = Either.right(Collections.emptyList());
        return result;
    }


    //d.
    @Override
    public int add(ReadArticle readArticle){
        int response=0;

        return response;
    }

    @Override
    public Map<Double, String> getAvgRating(int reader) {
        return null;
    }



}

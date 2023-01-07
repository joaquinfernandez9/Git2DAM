package dao.impl;

import dao.Const;
import dao.DaoReadArticle;
import model.ReadArticle;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReadArticleImpl implements DaoReadArticle {


    @Inject
    public DaoReadArticleImpl() {
    }

    @Override
    public Either<Integer, List<ReadArticle>> getAll() {
        Either<Integer, List<ReadArticle>> result = null;
        return result;
    }


    //1a4 Append a new ReadArticle: Check for integrity first
    @Override
    public int add(ReadArticle readArticle){
        int response=0;
        return response;
    }

    @Override
    public int delete(int id){
        int response=0;
        return response;
    }




}

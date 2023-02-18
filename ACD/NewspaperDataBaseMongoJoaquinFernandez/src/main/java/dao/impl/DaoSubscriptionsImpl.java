package dao.impl;

import dao.Const;
import dao.DaoSubscriptions;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import model.Subscription;
import org.bson.types.ObjectId;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoSubscriptionsImpl implements DaoSubscriptions {


    public DaoSubscriptionsImpl() {
    }

    @Override
    public int delete(int idReader, ObjectId idNewspaper) {
        int response;
        response = 1;
        return response;
    }

    public int add(Subscription subscription) {
        int response;
        response = 1;
        return response;
    }

    @Override
    public Either<Object, List<Subscription>> getAll() {
        Either<Object, List<Subscription>> result = null;

        return result;
    }

    @Override
    public Either<Object, List<Subscription>> getAll(int id) {
        return null;

    }



}

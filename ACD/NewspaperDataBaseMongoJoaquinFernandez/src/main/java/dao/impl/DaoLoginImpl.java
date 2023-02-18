package dao.impl;

import dao.Const;
import dao.DaoLogin;
import dao.DaoReader;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import model.Login;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class DaoLoginImpl implements DaoLogin {

    public DaoLoginImpl() {

    }

    @Override
    public int login(String user, String password) {
        return -1;
    }


    @Override
    public int delete(int id) {
        return -1;
    }


    //UPDATE READER
    @Override
    public int update(Login login) {
        return -1;
    }


    //ADD READER
    @Override
    public int add(Login login) {
        return 0;
    }

}

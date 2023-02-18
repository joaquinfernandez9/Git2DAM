package dao.impl;

import dao.DaoLogin;
import dao.database.DatabaseConnectionPool;
import dao.domain.ErrorDatabaseException;
import dao.domain.NotFoundException;
import domain.User;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.Const.DATABASE_NOT_AVAILABLE;

public class DaoLoginImpl implements DaoLogin {

    private final DatabaseConnectionPool daoBD;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public DaoLoginImpl(DatabaseConnectionPool daoBD, Pbkdf2PasswordHash passwordHash) {
        this.daoBD = daoBD;
        this.passwordHash = passwordHash;
    }

    @Override public User login(String username, String password) {
        try (Connection connection = daoBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM user WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User usuario = new User(username, password);
                usuario.setRole(resultSet.getString("rol"));
                return usuario;
            } else {
                throw new NotFoundException("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public User register(User usuario) {
        try (Connection connection = daoBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (username, password, role) VALUES (?, ?, ?)");
            preparedStatement.setString(1, usuario.getPassword());
            preparedStatement.setString(2, passwordHash.generate(usuario.getPassword().toCharArray()));
            preparedStatement.setString(3, "user");
            preparedStatement.executeUpdate();
            usuario.setRole("user");
            return usuario;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override
    public boolean correctPass(String password, String username){
        try (Connection connection = daoBD.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return passwordHash.verify(password.toCharArray(), resultSet.getString("password"));
            } else {
                throw new NotFoundException("Contraseña incorrectos");
            }
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }


}

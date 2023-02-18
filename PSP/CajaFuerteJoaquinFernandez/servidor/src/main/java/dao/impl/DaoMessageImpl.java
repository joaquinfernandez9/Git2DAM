package dao.impl;

import dao.DaoMessage;
import dao.database.DatabaseConnectionPool;
import dao.domain.ErrorDatabaseException;
import domain.Message;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static config.Const.DATABASE_NOT_AVAILABLE;

public class DaoMessageImpl implements DaoMessage {

    private final DatabaseConnectionPool daoDB;

    @Inject
    public DaoMessageImpl(DatabaseConnectionPool daoDB) {
        this.daoDB = daoDB;
    }

    @Override public Message insert(Message mensaje) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO message (id, cipherText, id_folder) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, mensaje.getId());
            preparedStatement.setString(2, mensaje.getCipherText());
            preparedStatement.setInt(3, mensaje.getId_folder());
            preparedStatement.executeUpdate();
            return mensaje;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public Message update(Message mensaje) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE message SET cipherText = ? WHERE id = ?");
            preparedStatement.setString(1, mensaje.getCipherText());
            preparedStatement.setInt(2, mensaje.getId());
            preparedStatement.executeUpdate();
            return mensaje;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public int delete(int id) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM message WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public Message getById(int id) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            return null;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public List<Message> getAll(int idCarpeta) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM message where id_folder = ?");
            preparedStatement.setInt(1, idCarpeta);
            preparedStatement.executeQuery();
            return null;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }
    
    
    
}

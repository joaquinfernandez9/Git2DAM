package dao.impl;

import dao.DaoFolder;
import dao.database.DatabaseConnectionPool;
import dao.domain.ErrorDatabaseException;
import domain.Vault;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static config.Const.DATABASE_NOT_AVAILABLE;

public class DaoFolderImpl implements DaoFolder {
    private final DatabaseConnectionPool daoDB;
    @Inject
    public DaoFolderImpl(DatabaseConnectionPool daoDB) {
        this.daoDB = daoDB;
    }

    @Override
    public List<Vault> getAll() {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vault");
            preparedStatement.executeQuery();
            return null;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    @Override public Vault insert(Vault folder) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vault (name,permissions,password,username) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, folder.getName());
            preparedStatement.setInt(2, folder.getPermission());
            preparedStatement.setString(3, folder.getPassword());
            preparedStatement.setString(4, folder.getUsername());
            preparedStatement.executeUpdate();
            folder.setId(preparedStatement.getGeneratedKeys().getInt(1));
            return folder;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }


    @Override public int delete(int id) {
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vault WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
    }

    //para el get pasar nombreCarpeta, passwordCarpeta,
    @Override
    public boolean checkPass(String password, int id){
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM vault WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            if (preparedStatement.getResultSet().getString(1).equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
        return false;
    }

    @Override
    public boolean checkUser(String user, int id){
        try (Connection connection = daoDB.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM vault WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            if (preparedStatement.getResultSet().getString(1).equals(user)) {
                return true;
            }
        } catch (SQLException e) {
            throw new ErrorDatabaseException(DATABASE_NOT_AVAILABLE);
        }
        return false;
    }


}

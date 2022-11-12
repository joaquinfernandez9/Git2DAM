package dao.impl;

import dao.DaoReadArticle;
import dao.dataBase.DataBaseConnectionPool;
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

    private final DataBaseConnectionPool db;

    @Inject
    public DaoReadArticleImpl(DataBaseConnectionPool db) {
        this.db = db;
    }


    @Override
    public Either<Integer, List<ReadArticle>> getAll() {
        Either<Integer, List<ReadArticle>> result;

        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = statement.executeQuery("select * from readarticle");

            if (rs != null) {
                result = Either.right(readRS(rs));
            } else {
                result = Either.left(-2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
            result = Either.left(-3);
        }

        return result;
    }

    @Override
    public Either<Integer, List<ReadArticle>> getAll(int id){
        Either<Integer, List<ReadArticle>> result;
        try (Connection con = db.getConnection();
        PreparedStatement preparedStatement = con.prepareStatement("select * from readarticle where id_reader=?")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                result = Either.right(readRS(rs));
            } else {
                result = Either.left(-2);
            }
        } catch (SQLException exception){
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, exception);
            result = Either.left(-3);
        }
        return result;
    }

    //1a4 Append a new ReadArticle: Check for integrity first
    @Override
    public int add(ReadArticle readArticle){
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement pS = con.prepareStatement("INSERT INTO readarticle" +
                     "(id_article, id_reader, ranking) VALUES (?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)
                ){
            pS.setInt(1, readArticle.getId_article());
            pS.setInt(2, readArticle.getId_reader());
            pS.setInt(3, readArticle.getRanking());

            response = pS.executeUpdate();
        }catch (SQLException e) {
            log.error(e.getMessage());
            response = -3;
        } catch (Exception e) {
            e.printStackTrace();
            response = -2;
        }
        return response;
    }

    @Override
    public int delete(int id){
        int response;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "delete from readarticle where id_reader =?")) {
            preparedStatement.setInt(1, id);
            // executeUpdate method for INSERT, UPDATE and DELETE
            response = preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(DaoReadArticleImpl.class.getName())
                    .log(Level.SEVERE, null, sqle);
            response = -3;
        }
        return response;
    }

    private List<ReadArticle> readRS(ResultSet rs) {
        List<ReadArticle> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int idArticle = rs.getInt("id_article");
                int idReader = rs.getInt("id_reader");
                int ranking = rs.getInt("ranking");
                ReadArticle ra = new ReadArticle(idReader, idArticle,ranking);
                response.add(ra);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}

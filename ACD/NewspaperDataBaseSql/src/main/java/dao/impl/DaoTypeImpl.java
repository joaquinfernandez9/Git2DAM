package dao.impl;

import config.ConfigProperties;
import dao.DaoType;
import dao.dataBase.DataBaseConnectionPool;
import dao.strings.DaoConstants;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ArticleType;
import lombok.extern.log4j.Log4j2;
import model.ReadArticle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoTypeImpl implements DaoType {

    private DataBaseConnectionPool pool;

    @Inject
    public DaoTypeImpl(DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override public List<ArticleType> getAll(){
        Path file= Paths.get(ConfigProperties
                .getInstance().getProperty(DaoConstants.PATH_TYPE));
        List<ArticleType> articlesList = new ArrayList<>();

        try{
            List<String> articles = Files.readAllLines(file);
            articles.forEach(article -> articlesList.add(new ArticleType(article)));
        } catch (IOException e){
            log.error(e.getMessage());
        }
        return articlesList;
    }

    @Override public ArticleType get(Integer id, String description){
        List<ArticleType> articleTypes = getAll();
        if (id == null){
            return articleTypes.stream()
                    .filter(linea -> linea.getDescription().equals(description))
                    .findFirst().orElse(null);
        } else {
            return articleTypes.stream()
                    .filter(type ->
                            type.getTypeID() == id)
                    .findFirst().orElse(null);
        }
    }

//    @Override
//    public Either<Integer, List<ArticleType>> getAll() {
//        Either<Integer, List<ArticleType>> response;
//        try (Connection con = pool.getConnection()){
//            PreparedStatement ps = con.prepareStatement("select * from type");
//            ResultSet rs = ps.executeQuery();
//            if (rs != null){
//                response = Either.right(readRS(rs));
//            } else {
//                response = Either.left(-2);
//            }
//        } catch (SQLException sql){
//            response = Either.left(-3);
//            log.error(sql.getMessage());
//        }
//
//        return response;
//    }
//
//    @Override
//    public Either<Integer, ArticleType> get(Integer id, String description) {
//        Either<Integer, ArticleType> response;
//        try (Connection con = pool.getConnection()) {
//            PreparedStatement ps = con.prepareStatement("select id from type where description=?");
//            ps.setString(1, description);
//            ResultSet rs = ps.executeQuery();
//            if (rs !=null){
//                response = Either.right(readRS(rs).get(0));
//            } else {
//                response = Either.left(-2);
//            }
//        } catch (SQLException e) {
//            response = Either.left(-3);
//            log.error(e.getMessage());
//        }
//        return response;
//    }
//
//
//    private List<ArticleType> readRS(ResultSet rs) {
//        List<ArticleType> response = new ArrayList<>();
//        try {
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String description = rs.getString("description");
//                ArticleType type = new ArticleType(id, description);
//                response.add(type);
//            }
//        } catch (SQLException e) {
//            log.error(e.getMessage());
//        }
//        return response;
//    }


}

package dao.impl;

import dao.DaoReadArticle;
import domain.modelo.ReadArticle;
import domain.modelo.Reader;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
public class DaoReadArticleImpl implements DaoReadArticle {

    private final DaoDB db;

    @Inject
    public DaoReadArticleImpl(DaoDB db) {
        this.db = db;
    }

    //    @Override
//    public Either<String, Boolean> add(int idReader, ReadArticle readArticle) {
//        Either<String, Boolean> respuesta;
//        try {
//            Path file = Paths.get(ConfigXML.getInstance()
//                    .getProperty(DaoConstants.XML_READER_PATH));
//
//            JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//
//            Readers lista = (Readers) unmarshaller.unmarshal(Files.newInputStream(file));
//
//            respuesta = Either.right(lista.getReaders().stream().filter(reader ->
//                    reader.getId() == idReader).peek(reader ->
//                    reader.getReadArticles()
//                            .getReadArticlesList().add(readArticle)).findFirst().isPresent());
//
//            jaxbMarshaller.marshal(lista,
//                    Files.newOutputStream(file));
//
//
//        } catch (Exception e) {
//            respuesta = Either.left(e.getMessage());
//        }
//        return respuesta;
//    }

    public Either<String, List<ReadArticle>> getAll() {
        Either<String, List<ReadArticle>> response = null;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
                ResultSet rs = statement.executeQuery("select * from readarticle");

                if (rs!=null){
                    response = Either.right(readRS(rs));
                } else {
                    response = Either.left("error");
                }


        } catch (SQLException ex) {
            Logger.getLogger(DaoReaderImpl.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return response;
    }


    @Override
    public List<ReadArticle> getReadArticles(int idReader) {
        List<ReadArticle> ra = new ArrayList<>();
        List<ReadArticle> all = getAll().get();

        all.forEach(readArticle -> {
            if (readArticle.getIdReader() == idReader){
                ra.add(readArticle);
            }
        });

        return ra;
    }

    @Override
    public int deleteReader(int id){
        int response = 0;
//        all.forEach(readArticle -> {
//            if (readArticle.getIdReader() == id){
//                all.remove(readArticle);
//            }
//        });
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "delete from readarticle where id_reader =?")) {
            preparedStatement.setInt(1, id);
            // executeUpdate method for INSERT, UPDATE and DELETE
            response = preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(DaoReadArticleImpl.class.getName())
                    .log(Level.SEVERE, null, sqle);
        }
        return response;
    }

    private List<ReadArticle> readRS(ResultSet rs) {
        List<ReadArticle> response = new ArrayList<>();
        try {
            while (rs.next()) {
                int id_article = rs.getInt("id_article");
                int id_reader = rs.getInt("id_reader");
                int ranking = rs.getInt("ranking");
                ReadArticle ra = new ReadArticle(id_article, id_reader,ranking);
                response.add(ra);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return response;
    }


}

package domain.services;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoReader;
import dao.DaoType;
import domain.modelo.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class ReaderServ {

    private final DaoNewspaper daoNewspaper;
    private final DaoReader daoReader;
    private final DaoArticle daoArticle;
    private final DaoType daoType;

    @Inject
    public ReaderServ(DaoNewspaper daoNewspaper, DaoReader daoReader,
                      DaoArticle daoArticle, DaoType daoType) {
        this.daoNewspaper = daoNewspaper;
        this.daoReader = daoReader;
        this.daoArticle = daoArticle;
        this.daoType = daoType;
    }


    public Either<String, List<Reader>> getAll(){
        Either<String, List<Reader>> respuesta;
        if (daoReader.getAll().isRight()){
            respuesta = Either.right(daoReader.getAll().get());
        } else {
            respuesta = Either.left("Error");
        }
        return respuesta;
    }

    //Get information about the readers subscribed to a specific newspaper
    public Either<String, List<Reader>> readersSubscribed(int idNewspaper) {
        List<Reader> readers = daoReader.getAll().get();
        Either<String, List<Reader>> respuesta;
        respuesta = Either.right(readers.stream()
                .filter(reader ->
                        reader.getSubscriptions().getSubscriptionsList()
                                .stream().filter(subscription ->
                                        subscription.getIdNewspaper() == idNewspaper).isParallel())
                .collect(Collectors.toList()));
        return respuesta;
    }

    //Get the readers of articles of a specific type
    public Either<String, List<Reader>> getReadersFromArticleType(String descType) {
        Either<String, List<Reader>> respuesta;
        ArticleType aT = daoType.get(null, descType);
        Article article = daoArticle.getAll().stream().filter(article1 ->
                article1.getTypeID() == aT.getTypeID()).findFirst().orElse(null);
        List<Reader> readers = daoReader.getAll().get();
        if (article != null) {
            respuesta = Either.right(
                    readers.stream().filter(reader ->
                                    reader.getReadArticles().getReadArticlesList()
                                            .stream().filter(readArticle ->
                                                    readArticle.getIdArticle() == article.getArticleID()).isParallel())
                            .collect(Collectors.toList())
            );
        } else {
            respuesta = Either.left("No va we");
        }


        return respuesta;
    }

    public Either<String, Boolean> appendReadArticle(int idReader, int idArticle, int rating){
        Either<String, Boolean> respuesta;

        Article article = daoArticle.getAll().stream()
                .filter(article1 ->
                        article1.getArticleID() == idArticle)
                .findFirst().orElse(null);
        Reader r;
        if (daoReader.get(idReader).isRight()){
            r = daoReader.get(idReader).get();

            if (article!= null){
                Newspaper np = daoNewspaper.get(article.getNewspaperID());
                Subscription sub = r.getSubscriptions().getSubscriptionsList()
                        .stream().filter(subscription ->
                                subscription.getIdNewspaper() == np.getNewspaperID())
                        .findFirst().orElse(null);

                if (sub!=null){
                    ReadArticle readArticle = new ReadArticle(r.getReadArticles().getReadArticlesList().size()+1,
                            r.getId(), article.getArticleID(), rating);
                    r.getReadArticles().getReadArticlesList().add(readArticle);
                    respuesta = Either.right(true);
                } else {
                    respuesta = Either.left("Error");
                }

            } else {
                respuesta = Either.left("Error");
            }
        } else {
            respuesta = Either.left("Error");
        }


        return respuesta;
    }


    public Either<String, Boolean> deleteReader(int idReader){
        Either<String, Boolean> respuesta;
        if (daoReader.delete(idReader).isRight()){
            respuesta = Either.right(true);
        } else {
            respuesta = Either.left("Error deleting user");
        }
        return respuesta;
    }


    //para la informacion de los lectores subscritos a un periodico en la lista de
    // lectores una caja de texto con el id del periodico

    //para los lectores de un tipo especifico de articulos otra pantalla con una lista de lectores y una caja de
    // texto que recibe la descripcion

    //agregar un articulo leido en la pantalla de add, cambiarle el nombre y eso,
    // meter un read article al lector seleccionado en la tabla

    //delete en una pantalla aparte


}

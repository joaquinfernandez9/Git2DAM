package domain.services;

import dao.*;
import domain.modelo.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderServ {

    private final DaoNewspaper daoNewspaper;
    private final DaoReader daoReader;
    private final DaoArticle daoArticle;
    private final DaoType daoType;
    private final DaoReadArticle daoReadArticle;

    @Inject
    public ReaderServ(DaoNewspaper daoNewspaper, DaoReader daoReader,
                      DaoArticle daoArticle, DaoType daoType,
                      DaoReadArticle daoReadArticle) {
        this.daoNewspaper = daoNewspaper;
        this.daoReader = daoReader;
        this.daoArticle = daoArticle;
        this.daoType = daoType;
        this.daoReadArticle = daoReadArticle;
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
    public List<Reader> readersSubscribed(int idNewspaper) {
        List<Reader> readers = daoReader.getAll().get();
        List<Reader> respuesta = new ArrayList<>();

       readers.forEach(reader ->
               reader.getSubscriptions().getSubscriptionsList().forEach(subscription -> {
                   if (subscription.getIdNewspaper() == idNewspaper){
                       respuesta.add(reader);
                   }
               }));

        return respuesta;
    }

    //Get the readers of articles of a specific type
    public List<Reader> getReadersFromArticleType(String descType) {
        ArticleType articleType = daoType.get(null, descType);

        List<Reader> readers = getAll().get();
        List<Reader> solution = new ArrayList<>();

        List<Article> articles = daoArticle.getAll()
                .stream().filter(article ->
                        article.getTypeID() == articleType.getTypeID()).collect(Collectors.toList());

        readers.forEach(reader -> articles.forEach(article -> {
            if (article.getArticleID() == reader.getReadArticles()
                    .getReadArticlesList().get(0).getIdArticle()){
                solution.add(reader);
            }
        }));
        return solution;

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
                    //crear un DaoReadArticle, hacer un add ahi y meterle al id del lector
                    // el article dado no es tan dificil coño vamos
                    if (daoReadArticle.add(r.getId(), readArticle).isRight()){
                        respuesta = Either.right(true);
                    } else {
                        respuesta = Either.left("error");
                    }
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



}

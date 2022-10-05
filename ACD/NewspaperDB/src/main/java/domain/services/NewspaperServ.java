package domain.services;

import dao.DaoArticle;
import dao.DaoNewspaper;
import domain.modelo.Article;
import domain.modelo.Newspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class NewspaperServ {

    private final DaoNewspaper daoNewspaper;
    private final DaoArticle daoArticle;

    @Inject
    public NewspaperServ(DaoNewspaper daoNewspaper,
                         DaoArticle daoArticle) {
        this.daoNewspaper = daoNewspaper;
        this.daoArticle = daoArticle;
    }

    public List<Newspaper> getAll() {
        return daoNewspaper.getAll();
    }


    public Either<String, Boolean> deleteNewspaper(int id) {
        List<Article> articles = daoArticle.getAll();
        Either<String, Boolean> respuesta;
        articles.forEach(article -> {
            if (article.getNewspaperID() == id){
                daoArticle.delete(article.getArticleID());

            }
        });
        if (daoNewspaper.delete(id).isRight()){
            respuesta = Either.right(true);
        } else {
            respuesta = Either.left("Cant operate");
        }
        return respuesta;
    }

    public boolean newspaperContainsArticles(int idNewspaper) {
        List<Article> articles = daoArticle.getAll();
        Newspaper np = daoNewspaper.get(idNewspaper);
        List<Article> articlesContian = articles.stream().filter(article ->
                article.getNewspaperID() == np.getNewspaperID())
                .collect(Collectors.toList());
        return !articlesContian.isEmpty();
    }

    public List<Article> articlesNewspaper(int id){
        List<Article> articles = daoArticle.getAll();
        Newspaper np = daoNewspaper.get(id);
        return articles.stream().filter(article ->
                        article.getNewspaperID() == np.getNewspaperID())
                .collect(Collectors.toList());
    }




}

package services.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import model.Article;
import model.Newspaper;
import services.NewspaperServ;
import services.strings.ServConstants;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class NewspaperServImpl implements NewspaperServ {

    private final DaoNewspaper daoNewspaperImpl;
    private final DaoArticle daoArticleImpl;

    @Inject
    public NewspaperServImpl(DaoNewspaper daoNewspaperImpl,
                         DaoArticle daoArticleImpl) {
        this.daoNewspaperImpl = daoNewspaperImpl;
        this.daoArticleImpl = daoArticleImpl;
    }

    @Override public List<Newspaper> getAll() {
        return daoNewspaperImpl.getAll();
    }


    @Override public Either<String, Boolean> deleteNewspaper(int id) {
        List<Article> articles = daoArticleImpl.getAll();
        Either<String, Boolean> respuesta;
        articles.forEach(article -> {
            if (article.getNewspaperID() == id){
                daoArticleImpl.delete(article.getArticleID());

            }
        });
        if (daoNewspaperImpl.delete(id).isRight()){
            respuesta = Either.right(true);
        } else {
            respuesta = Either.left(ServConstants.CANT_OPERATE);
        }
        return respuesta;
    }

    @Override public boolean newspaperContainsArticles(int idNewspaper) {
        List<Article> articles = daoArticleImpl.getAll();
        Newspaper np = daoNewspaperImpl.get(idNewspaper);
        List<Article> articlesContian = articles.stream().filter(article ->
                article.getNewspaperID() == np.getNewspaperID())
                .collect(Collectors.toList());
        return !articlesContian.isEmpty();
    }


}

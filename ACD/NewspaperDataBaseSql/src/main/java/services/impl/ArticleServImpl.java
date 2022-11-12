package services.impl;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import model.Article;
import model.ArticleType;
import model.Newspaper;
import services.ArticleServ;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServImpl implements ArticleServ {

    private final DaoArticle daoArticleImpl;
    private final DaoType daoTypeImpl;
    private final DaoNewspaper daoNewspaperImpl;

    @Inject
    public ArticleServImpl(DaoArticle daoArticleImpl, DaoType daoTypeImpl,
                           DaoNewspaper daoNewspaperImpl) {
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoNewspaperImpl = daoNewspaperImpl;
    }

    @Override
    public List<Article> getAll() {
        return daoArticleImpl.getAll();
    }

    @Override
    public List<Article> getArticlesOfAReader(int idReader) {
        return daoArticleImpl.getArticlesOfAReader(idReader);
    }

    //show articles by type
    @Override
    public List<Article> getArticlesFilter(String description) {
        ArticleType type = daoTypeImpl.getAll().stream()
                .filter(articleType ->
                        articleType.getDescription().equals(description))
                .collect(Collectors.toList()).get(0);
        return daoArticleImpl.getAll(type.getId());
    }

    @Override
    public int addArticle(Article a) {
        List<Article> articles = getAll();
        int response;
        ArticleType art = daoTypeImpl.getAll().stream().filter(
                articleType -> articleType.getId() == a.getId_type()
        ).findFirst().orElse(null);
        Newspaper np = daoNewspaperImpl.get(a.getId_newspaper());
        if (!articles.contains(a) && np != null && art != null) {
            response = daoArticleImpl.save(a);
        } else {
            response = -1;
        }
        return response;
    }


}



package domain.services;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import domain.modelo.Article;
import domain.modelo.ArticleType;
import domain.modelo.Newspaper;
import jakarta.enterprise.inject.New;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleServ {

    private final DaoArticle daoArticle;
    private final TypeServ typeServ;
    private final NewspaperServ newspaperServ;

    @Inject
    public ArticleServ(DaoArticle daoArticle, TypeServ typeServ,
                       NewspaperServ newspaperServ) {
        this.daoArticle = daoArticle;
        this.typeServ = typeServ;
        this.newspaperServ = newspaperServ;
    }



    public List<Article> getAll() {
        return daoArticle.getAll();
    }

    public List<Article> getArticlesFilter(String description) {
        List<Article> articlesList = getAll();
        ArticleType articleTypes = typeServ.getFilter(description);
        if (articleTypes == null) {
            return Collections.emptyList();
        } else {
            return articlesList.stream()
                    .filter(article ->
                            article.getTypeID() == articleTypes.getTypeID())
                    .collect(Collectors.toList());
        }
    }

    public void addArticle(Article a) {
        List<Article> articles = getAll();
        ArticleType art = typeServ.getByID(a.getTypeID());
        Newspaper np = newspaperServ.getByID(a.getNewspaperID());
        if (!articles.contains(a) && np != null && art != null) {
            daoArticle.save(a);
        }
    }

}



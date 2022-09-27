package domain.services;

import dao.DaoArticle;
import dao.DaoNewspaper;
import dao.DaoType;
import domain.modelo.Article;
import domain.modelo.ArticleType;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleServ {

    private final DaoArticle daoArticle;
    private final DaoType daoType;
    private final DaoNewspaper daoNewspaper;

    @Inject
    public ArticleServ(DaoArticle daoArticle, DaoType daoType,
                       DaoNewspaper daoNewspaper) {
        this.daoArticle = daoArticle;
        this.daoType = daoType;
        this.daoNewspaper = daoNewspaper;
    }


    public List<Article> getAll(){
        return daoArticle.getAll();
    }

    public List<Article> getArticlesFilter(String description){
        return daoArticle.getArticlesFilter(description);
    }

    public boolean addArticle(Article a){

        List<Article> articles = getAll();

        List<ArticleType> types = daoType.getAll();
        ArticleType articleType = types.stream()
                .filter(type ->
                        type.getTypeID() == a.getTypeID())
                .findFirst().orElse(null);

        List<Newspaper> newspapers = daoNewspaper.getAll();
        Newspaper newspaper = newspapers.stream()
                .filter(np ->
                        np.getNewspaperID() == a.getNewspaperID())
                .findFirst().orElse(null);

        if (!articles.contains(a) && newspaper!=null
        && articleType!=null){
            return daoArticle.save(a);
        } else return false;

    }

//    La idea era usarlo en el newspaperServ para comprobar el delete
//    public List<Article> getArticlesByNewspaperID(int id){
//        List<Article> articles = getAll();
//        return articles.stream().filter(article ->
//                article.getArticleID() == id)
//                .collect(Collectors.toList());
//    }


}

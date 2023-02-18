package services;

import model.Article;
import model.ArticleType;
import model.Newspaper;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface ArticleServ {
    List<Article> getAll();
    List<Article> getAll(ObjectId id);

    List<Article> getArticlesOfAReader(int idReader);

    List<Article> getArticlesFilter(String description);

    int addArticle(Article a, Newspaper np);

    int deleteArticle(Article id, Newspaper np);

    List<Article> deleteArticle(ObjectId id);



    int updateArticle(Article a, Newspaper np, String description);
}

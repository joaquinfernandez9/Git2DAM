package dao;

import model.Article;
import model.Newspaper;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface DaoArticle {
    List<Article> getAll();

    List<Article> getAll(ObjectId newspaper);



    Article get(Article a, Newspaper newspaper);

    int save(Article a, Newspaper newspaper);

    int delete(Article art, Newspaper newspaper);

    List<Article> delete(ObjectId id);

    int update(Article a, Newspaper newspaper, String desc);

}

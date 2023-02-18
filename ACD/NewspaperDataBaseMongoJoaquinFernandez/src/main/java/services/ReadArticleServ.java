package services;

import model.ReadArticle;

import java.util.List;
import java.util.Map;

public interface ReadArticleServ {

    List<ReadArticle> getAll(int id);

    int appendReadArticle(ReadArticle readArticle);

    Map<Double, String> getAvgRating(int reader);
}

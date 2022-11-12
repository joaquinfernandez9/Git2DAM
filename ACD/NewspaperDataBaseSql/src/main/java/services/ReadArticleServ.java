package services;

import model.ReadArticle;

import java.util.List;

public interface ReadArticleServ {

    List<ReadArticle> getAll(int id);

    int appendReadArticle(ReadArticle readArticle);
}

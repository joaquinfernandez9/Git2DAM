package services;

import model.Newspaper;

import java.util.List;

public interface NewspaperServ {
    List<Newspaper> getAll();

    int deleteNewspaper(int id);

    int newspaperContainsArticles(int idNewspaper);

    int add(Newspaper n);

    int update(Newspaper n);
}
package dao;

import model.Newspaper;

import java.util.List;
import java.util.Map;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Newspaper get(int id);

    int add(Newspaper n);

    Map<String, Integer> getNbrArticles(int newspaper);

    int delete(int id);


    int update(Newspaper n);
}

package dao;

import model.Newspaper;

import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Newspaper get(int id);

    int add(Newspaper n);

    int delete(int id);


    int update(Newspaper n);
}

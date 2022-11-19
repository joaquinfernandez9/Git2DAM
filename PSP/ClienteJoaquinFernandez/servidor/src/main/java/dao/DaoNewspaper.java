package dao;

import model.Newspaper;

import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Newspaper get(int id);

    Newspaper add(Newspaper n);

    int delete(int id);

    Newspaper update(Newspaper n);
}

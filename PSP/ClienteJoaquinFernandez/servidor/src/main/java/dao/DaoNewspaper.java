package dao;

import model.Newspaper;

import java.util.List;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Newspaper get(int id);

    Newspaper add(Newspaper n);

    void deleteConfirmed(int id);

    void delete(int id);

    Newspaper update(Newspaper n);
}

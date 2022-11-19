package domain.services;

import model.Newspaper;

import java.util.List;

public interface NewspaperServ {
    List<Newspaper> getAll();

    int deleteNewspaper(int id);

    Newspaper add(Newspaper n);

    Newspaper update(Newspaper n);

    Newspaper get(int id);
}

package domain.services;

import model.Reader;

import java.util.List;

public interface ReaderServ {
    List<Reader> getAll(int idNews, String description);

    Reader get(int id);

    void deleteReader(int id);

    Reader update(Reader reader);

    Reader add(Reader reader);

    int appendReadArticle(Reader reader, int article, int rating);
}

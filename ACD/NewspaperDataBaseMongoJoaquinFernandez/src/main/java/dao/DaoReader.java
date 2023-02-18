package dao;

import model.Article;
import model.ArticleType;
import model.Newspaper;
import model.Reader;
import io.vavr.control.Either;

import java.util.List;

public interface DaoReader {
    List<Reader> getAll();

    List<Reader> getAll(Newspaper idNews);


    //no usar
    List<Reader> getAll(String description);

    Reader get(int id);

    int delete(Newspaper np, Reader r);

    int save(Reader r, Newspaper np);

    int update(Reader r);


}

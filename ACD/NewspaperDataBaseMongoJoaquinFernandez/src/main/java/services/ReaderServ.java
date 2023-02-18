package services;

import model.*;
import io.vavr.control.Either;

import java.time.LocalDate;
import java.util.List;

public interface ReaderServ {
    List<Reader> getAll();
    List<Reader> getAll(Newspaper idNews);
    List<Reader> getAll(String type);


    Reader get(int id);

    int deleteReader(Newspaper np, Reader idReader);

    int update(Reader reader);

    int add(Reader r, Newspaper np);


//    List<Reader> getReadersFromArticleType(String descType);

}

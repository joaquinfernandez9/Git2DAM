package domain.services.impl;

import dao.*;
import domain.modelo.Reader;
import domain.services.ReaderServ;
import domain.services.strings.ServConstants;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public class ReaderServImpl implements ReaderServ {

    private final DaoNewspaper daoNewspaperImpl;
    private final DaoReader daoReaderImpl;
    private final DaoArticle daoArticleImpl;
    private final DaoType daoTypeImpl;
    private final DaoReadArticle daoReadArticleImpl;

    @Inject
    public ReaderServImpl(DaoNewspaper daoNewspaperImpl, DaoReader daoReaderImpl,
                          DaoArticle daoArticleImpl, DaoType daoTypeImpl,
                          DaoReadArticle daoReadArticleImpl) {
        this.daoNewspaperImpl = daoNewspaperImpl;
        this.daoReaderImpl = daoReaderImpl;
        this.daoArticleImpl = daoArticleImpl;
        this.daoTypeImpl = daoTypeImpl;
        this.daoReadArticleImpl = daoReadArticleImpl;
    }

    @Override
    public Either<String, List<Reader>> getAll() {
        return daoReaderImpl.getAll();
    }

    @Override
    public Either<String, Reader> get(int id){
        return daoReaderImpl.get(id);
    }
//    @Override public List<Reader> readersSubscribed(int idNewspaper) {
//        List<Reader> readers = daoReaderImpl.getAll().get();
//        List<Reader> respuesta = new ArrayList<>();
//        readers.forEach(reader ->
//                reader.getSubscriptions().getSubscriptionsList().forEach(subscription -> {
//                    if (subscription.getIdNewspaper() == idNewspaper) {
//                        respuesta.add(reader);
//                    }
//                }));
//        return respuesta;
//    }


//    @Override public List<Reader> getReadersFromArticleType(String descType) {
//        ArticleType articleType = daoTypeImpl.get(null, descType);
//        //esto
//        //
//
//        List<Reader> readers = getAll().get();
//        List<Reader> solution = new ArrayList<>();
//
//        if (articleType != null) {
//            List<Article> articles = daoArticleImpl.getAll()
//                    .stream().filter(article ->
//                            article.getTypeID() == articleType.getTypeID())
//                    .collect(Collectors.toList());
//
//            readers.forEach(reader ->
//                    reader.getReadArticles().getReadArticlesList()
//                            .forEach(readArticle ->
//                                    articles.forEach(article -> {
//                                        if (article.getArticleID() == readArticle.getIdArticle()
//                                                && !solution.contains(reader)) {
//                                            solution.add(reader);
//                                        }
//                                    }))
//            );
//
//        }
//        return solution;
//    }

//    @Override public boolean appendReadArticle(int idReader, int idArticle, int rating) {
//        boolean response = false;
//        Article article = daoArticleImpl.getAll().stream()
//                .filter(article1 ->
//                        article1.getArticleID() == idArticle)
//                .findFirst().orElse(null);
//        Reader r;
//        if (daoReaderImpl.get(idReader).isRight()) {
//            r = daoReaderImpl.get(idReader).get();
//
//            if (article != null) {
//                Newspaper np = daoNewspaperImpl.get(article.getNewspaperID());
//                Subscription sub = r.getSubscriptions().getSubscriptionsList()
//                        .stream().filter(subscription ->
//                                subscription.getIdNewspaper() == np.getNewspaperID())
//                        .findFirst().orElse(null);
//                if (sub != null && sub.getCancellationDate()==null) {
//                    ReadArticle readArticle;
//                    if (r.getReadArticles().getReadArticlesList()== null){
//                        r.setReadArticles(new ReadArticles());
//                        readArticle = new ReadArticle(1,
//                                r.getId(), article.getArticleID(), rating);
//                    } else {
//                        readArticle = new ReadArticle(r.getReadArticles()
//                                .getReadArticlesList().size() + 1,
//                                r.getId(), article.getArticleID(), rating);
//                    }
//                    daoReadArticleImpl.add(r.getId(), readArticle).isRight();
//                    response = true;
//
//                }
//            }
//        }
//        return response;
//    }

    @Override
    public int deleteReader(int idReader) {
        return daoReaderImpl.delete(idReader);
    }

    @Override
    public boolean update(int id, String name) {
        return daoReaderImpl.update(id, name);
    }

    @Override
    public int add(int id, String name, LocalDate date) {
        return daoReaderImpl.add(id, name, date);
    }


}

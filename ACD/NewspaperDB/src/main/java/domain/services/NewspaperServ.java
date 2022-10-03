package domain.services;

import dao.DaoNewspaper;
import domain.modelo.Article;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class NewspaperServ {

    private final DaoNewspaper daoNewspaper;
    private final ArticleServ articleServ;

    @Inject
    public NewspaperServ(DaoNewspaper daoNewspaper,
                         ArticleServ articleServ){
        this.daoNewspaper = daoNewspaper;
        this.articleServ = articleServ;
    }

    public List<Newspaper> getAll(){
        return daoNewspaper.getAll();
    }

    public void deleteNewspaper(int news){
        daoNewspaper.deleteNewspaper(news);
    }

    public Newspaper getByID(int id){
        List<Newspaper> newspapers = daoNewspaper.getAll();
        return newspapers.stream()
                .filter(np ->
                        np.getNewspaperID() == id)
                .findFirst().orElse(null);

    }

    public List<Article> newspaperContainsArticles(int idNewspaper){


        List<Article> articles = articleServ.getAll();
        Newspaper np = getByID(idNewspaper);

        if (np==null){
            return Collections.emptyList();
        } else {
            //lista de articulos con el id del periodico
            return articles.stream().filter(article ->
                    article.getNewspaperID() == np.getNewspaperID())
                    .collect(Collectors.toList());
        }
    }

    public boolean borrar(boolean respuesta, int id){
        if (respuesta){
            List<Article> articlesTotal = articleServ.getAll();
            List<Article> articlesDelete = newspaperContainsArticles(id);
            articlesTotal = articlesTotal.stream()
                    .peek(articlesDelete::remove).collect(Collectors.toList());
            return !new HashSet<>(articlesTotal).containsAll(articlesDelete);

        } else {
            return false;
        }
    }

}

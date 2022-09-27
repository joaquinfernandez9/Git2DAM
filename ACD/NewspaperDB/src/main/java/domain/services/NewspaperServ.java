package domain.services;

import dao.DaoNewspaper;
import domain.modelo.Article;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;

import java.util.Collections;
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

    public boolean deleteNewspaper(int news){
        return daoNewspaper.deleteNewspaper(news);
    }

    public List<Article> newspaperContainsArticles(int idNewspaper){
        List<Newspaper> npList = getAll();
        List<Article> articles = articleServ.getAll();
        Newspaper np = npList.stream().filter(newspaper ->
                newspaper.getNewspaperID() == idNewspaper)
                .findFirst().orElse(null);
        if (np==null){
            return Collections.emptyList();
        } else {
            //lista de articulos con el id del periodico
            return articles.stream().filter(article ->
                    article.getNewspaperID() == np.getNewspaperID())
                    .collect(Collectors.toList());
        }
    }

}

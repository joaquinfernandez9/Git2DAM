package ui.pantallas.newspaper.list;

import model.Article;
import model.Newspaper;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewsState {
    private final String error;
    private final boolean change;
    private final List<Newspaper> newspaperList;
    private  List<Article> articleList;
    private Map<String, Integer> nbrArticles;



    public NewsState(List<Newspaper> newspapers, String error, boolean change, List<Article> articles) {
        this.newspaperList = newspapers;
        this.error = error;
        this.change = change;
        this.articleList = articles;
    }

    public NewsState(List<Newspaper> newspapers, String error, boolean change, Map<String, Integer> nbrArticles) {
        this.newspaperList = newspapers;
        this.error = error;
        this.change = change;
        this.nbrArticles = nbrArticles;
    }

    public NewsState(List<Newspaper> newspapers, String error, boolean change) {
        this.newspaperList = newspapers;
        this.error = error;
        this.change = change;
    }
}

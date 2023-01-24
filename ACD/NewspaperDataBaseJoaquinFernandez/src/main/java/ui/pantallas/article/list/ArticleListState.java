package ui.pantallas.article.list;

import model.Article;
import lombok.Data;
import model.ArticleType;

import java.util.List;

@Data
public class ArticleListState {
    private final String error;
    private final boolean change;
    private final List<Article> articleList;

    private ArticleType type;

    public ArticleListState(String error, boolean change, List<Article> articleList, ArticleType type) {
        this.error = error;
        this.change = change;
        this.articleList = articleList;
        this.type = type;
    }
}

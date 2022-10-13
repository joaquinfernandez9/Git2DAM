package ui.pantallas.article.list;

import domain.modelo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleListState {
    private final String error;
    private final boolean change;
    private final List<Article> articleList;
}

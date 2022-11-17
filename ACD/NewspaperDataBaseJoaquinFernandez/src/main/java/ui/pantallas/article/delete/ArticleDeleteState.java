package ui.pantallas.article.delete;

import lombok.Data;
import model.Article;

import java.util.List;

@Data
public class ArticleDeleteState {
    private final String error;
    private final boolean change;
    private final List<Article> articleList;
}

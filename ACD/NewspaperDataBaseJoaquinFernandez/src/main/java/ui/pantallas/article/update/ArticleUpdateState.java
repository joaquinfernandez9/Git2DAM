package ui.pantallas.article.update;

import lombok.Data;
import model.Article;

import java.util.List;

@Data
public class ArticleUpdateState {
    private final String error;
    private final boolean change;
    private final List<Article> articleList;
}

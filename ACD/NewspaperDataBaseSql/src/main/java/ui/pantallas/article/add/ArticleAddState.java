package ui.pantallas.article.add;

import domain.modelo.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleAddState {
        private final String error;
        private final boolean change;
        private final List<Article> articleList;
}

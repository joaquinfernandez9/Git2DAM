package ui.pantallas.article.add;

import domain.modelo.Article;
import domain.modelo.Newspaper;
import domain.services.ArticleServ;
import lombok.Data;

import java.util.List;

@Data
public class ArticleAddState {
        private final String error;
        private final boolean change;
        private final List<Article> articleList;
}

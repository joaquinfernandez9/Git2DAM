package ui.pantallas.reader.appendArticle;

import model.Article;
import model.ReadArticle;
import lombok.Data;

import java.util.List;

@Data
public class AppendArticleState {
    private final String error;
    private final boolean change;
    private final List<ReadArticle> readArticleList;
    private final List<Article> articleList;
}

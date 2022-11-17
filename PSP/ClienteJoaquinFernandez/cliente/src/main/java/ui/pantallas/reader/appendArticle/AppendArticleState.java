package ui.pantallas.reader.appendArticle;

import lombok.Data;
import model.Article;
import model.ReadArticle;

import java.util.List;

@Data
public class AppendArticleState {
    private final String error;
    private final boolean change;
    private final List<ReadArticle> readArticleList;
    private final List<Article> articleList;
}

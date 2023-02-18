package ui.pantallas.article.addDeleteUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.Article;
import model.Newspaper;

import java.util.List;

@Data
@AllArgsConstructor
public class State {
    private List<Article> articles;
    private List<Newspaper> newspapers;
    private String error;
}

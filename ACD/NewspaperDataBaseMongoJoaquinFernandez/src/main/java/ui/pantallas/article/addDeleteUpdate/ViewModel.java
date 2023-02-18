package ui.pantallas.article.addDeleteUpdate;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Article;
import model.Newspaper;
import org.bson.types.ObjectId;
import services.ArticleServ;
import services.NewspaperServ;

import java.util.List;

public class ViewModel {
    private final ArticleServ articleServ;
    private final NewspaperServ newspaperServ;
    private final ObjectProperty<State> state;


    @Inject
    public ViewModel(ArticleServ articleServ, NewspaperServ newspaperServ) {
        this.articleServ = articleServ;
        this.newspaperServ = newspaperServ;
        state = new SimpleObjectProperty<>(new State(null, null, null));
    }

    public ReadOnlyObjectProperty<State> getState() {
        return state;
    }


    public void addArticle(Article article, Newspaper newspaper) {
        articleServ.addArticle(article, newspaper);
    }

    public void deleteArticle(Article article, Newspaper newspaper) {
        articleServ.deleteArticle(article, newspaper);
    }

    public void updateArticle(Article article, Newspaper newspaper, String desc) {
        articleServ.updateArticle(article, newspaper, desc);

    }

    public List<Article> loadArticle(ObjectId id) {
        return articleServ.getAll(id);
    }

    public List<Newspaper> loadNewspaper() {
        return newspaperServ.getAll();
    }

    public void nullState() {
        state.setValue(new State(null, null, null));
    }

}

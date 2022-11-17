package ui.pantallas.article.update;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Article;
import services.ArticleServ;

public class ArticleUpdateViewModel {

    private final ArticleServ articleServImpl;
    private final ObjectProperty<ArticleUpdateState> state;

    @Inject
    public ArticleUpdateViewModel(ArticleServ articleServImpl) {
        this.articleServImpl = articleServImpl;
        this.state = new SimpleObjectProperty<>(
                new ArticleUpdateState(null, false,
                        articleServImpl.getAll()));
    }


    public ObjectProperty<ArticleUpdateState> getState() {
        return state;
    }

    public void load() {
        state.setValue(new
                ArticleUpdateState(null, !state.get().isChange(),
                articleServImpl.getAll()));
    }

    public void update(int id, String nameArticle, int idType, int idNewspaper) {
        Article art = new Article(id, nameArticle, idNewspaper, idType);
        int response = articleServImpl.updateArticle(art);
        if (response == 1) {
            state.setValue(new
                    ArticleUpdateState("Article updated correctly", !state.get().isChange(),
                    articleServImpl.getAll()));
        } else {
            state.setValue(new
                    ArticleUpdateState("Error updating article", !state.get().isChange(),
                    articleServImpl.getAll()));
        }
        load();
    }


}

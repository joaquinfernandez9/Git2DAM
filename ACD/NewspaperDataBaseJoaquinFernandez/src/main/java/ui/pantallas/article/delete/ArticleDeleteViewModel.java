package ui.pantallas.article.delete;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import services.ArticleServ;

public class ArticleDeleteViewModel {

    private final ArticleServ articleServImpl;
    private final ObjectProperty<ArticleDeleteState> state;

    @Inject
    public ArticleDeleteViewModel(ArticleServ articleServImpl) {
        this.articleServImpl = articleServImpl;
        this.state = new SimpleObjectProperty<>(
                new ArticleDeleteState(null, false,
                        articleServImpl.getAll()));
    }

    public void load() {
        state.setValue(new
                ArticleDeleteState(null, !state.get().isChange(),
                articleServImpl.getAll()));
    }

    public ObjectProperty<ArticleDeleteState> getState() {
        return state;
    }

    public void delete(int id) {
        //esto es int comprender errores
//        int response = articleServImpl.deleteArticle(id);
//        if (response == 1) {
//            state.setValue(new
//                    ArticleDeleteState("Article deleted correctly", !state.get().isChange(),
//                    articleServImpl.getAll()));
//        } else {
//            state.setValue(new
//                    ArticleDeleteState("Error deleting article", !state.get().isChange(),
//                    articleServImpl.getAll()));
//        }
//        load();
    }


}

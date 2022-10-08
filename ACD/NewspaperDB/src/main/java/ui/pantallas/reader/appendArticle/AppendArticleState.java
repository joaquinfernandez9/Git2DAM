package ui.pantallas.reader.appendArticle;

import domain.modelo.Reader;
import lombok.Data;

import java.util.List;

@Data
public class AppendArticleState {
    private final String error;
    private final boolean change;
    private final List<Reader> readerList;
}

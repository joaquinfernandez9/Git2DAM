package ui.pantallas.querys;

import lombok.Data;
import model.querys.QueryArticleRating;
import model.querys.QueryArticlesNewspaper;

import java.util.List;

@Data
public class QueryState {
    private final String error;
    private final boolean change;
    private final List<QueryArticlesNewspaper> thirdQuery;
    private final List<QueryArticleRating> fourthQuery;


}

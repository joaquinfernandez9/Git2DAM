package aggregations;

import org.bson.Document;

import java.util.List;

public interface DaoAggregations {
    //a. Get the id of the readers of articles of a specific type
    List<Document> getReadersByType(String type);

    //b. Get the average rating of the articles read by a reader, group by newspaper
    List<Document> getAverageRatingByReaderAndNewspaper(Integer id);

    //c. Get the type of articles that are most read
    Document getTypeMostRead();

    //d. Show a list with the number of articles of each type of the selected newspaper
    List<Document> getNmbrArticlesofTypeNewspaper(String name);

    //e. Get the description and the number of readers of each article
    List<Document> getDescNmbrReadersOfArticle();

    //f. Get the name of the 100 oldest subscriptors of newspaper Tempo
    List<Document> getNameTenOldestNewspapers();
}

package aggregations;

import com.mongodb.client.*;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoAggregationsImpl implements DaoAggregations {

    private final MongoCollection<Document> collection;

    @Inject
    public DaoAggregationsImpl() {
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase database = mongoClient.getDatabase("JoaquinFernandez");
        collection = database.getCollection("nespapers");
    }


    //a. Get the id of the readers of articles of a specific type
    @Override
    public List<Document> getReadersByType(String type) {
        List<Document> pipeline = Arrays.asList(new Document("$unwind", "$articles"), new Document("$match", new Document("articles.type", type)), new Document("$unwind", "$articles.readArticles"), new Document("$group", new Document("_id", "$articles.readArticles.id").append("reader_ids", new Document("$push", "$articles.readArticles.id"))));

        AggregateIterable<Document> result = collection.aggregate(pipeline);
        List<Document> readers = new ArrayList<>();
        for (Document doc : result) {
            readers.add(doc);
        }
        return readers;
    }
    //b. Get the average rating of the articles read by a reader, group by newspaper
    @Override
    public List<Document> getAverageRatingByReaderAndNewspaper(Integer id) {
        List<Document> pipeline = Arrays.asList(new Document("$unwind", "$articles"), new Document("$unwind", "$articles.readArticles"), new Document("$match", new Document("articles.readArticles.id", id)), new Document("$group", new Document("_id", "$articles.readArticles.id").append("average", new Document("$avg", "$articles.readArticles.rating"))));

        AggregateIterable<Document> result = collection.aggregate(pipeline);
        List<Document> readers = new ArrayList<>();
        for (Document doc : result) {
            readers.add(doc);
        }
        return readers;

    }

    //c. Get the type of articles that are most read
    @Override
    public Document getTypeMostRead() {
        List<Document> pipeline = Arrays.asList(new Document("$unwind", "$articles"), new Document("$unwind", "$articles.readArticles"), new Document("$group", new Document("_id", "$articles.type").append("count", new Document("$sum", 1))), new Document("$sort", new Document("Times read", -1)), new Document("$limit", 1));

        AggregateIterable<Document> result = collection.aggregate(pipeline);
        Document type = new Document();
        for (Document doc : result) {
            type = doc;
        }
        return type;
    }

    //d. Show a list with the number of articles of each type of the selected newspaper
    @Override
    public List<Document> getNmbrArticlesofTypeNewspaper(String name) {
        List<Document> pipeline = Arrays.asList(new Document("$match", new Document("name", name)), new Document("$unwind", "$articles"), new Document("$group", new Document("_id", "$articles.type").append("count", new Document("$sum", 1L))), new Document("$sort", new Document("count", -1L)));

        AggregateIterable<Document> result = collection.aggregate(pipeline);
        List<Document> articles = new ArrayList<>();
        for (Document doc : result) {
            articles.add(doc);
        }
        return articles;
    }
    //e. Get the description and the number of readers of each article
    @Override
    public List<Document> getDescNmbrReadersOfArticle() {
        List<Document> pipeline = Arrays.asList(new Document("$unwind", "$articles"), new Document("$unwind", "$articles.readArticles"), new Document("$group", new Document("_id", "$articles.desc").append("numberOfReaders", new Document("$sum", 1))), new Document("$sort", new Document("numberOfReaders", -1)));
        AggregateIterable<Document> result = collection.aggregate(pipeline);
        List<Document> articles = new ArrayList<>();
        for (Document doc : result) {
            articles.add(doc);
        }
        return articles;
    }
    //f. Get the name of the 100 oldest subscriptors of newspaper Tempo
    @Override
    public List<Document> getNameTenOldestNewspapers(){
        List<Document> pipeline = Arrays.asList(new Document("$sort", new Document("relDate", 1)), new Document("$limit", 10), new Document("$project", new Document("name", 1).append("_id", 0).append("relDate", 1)));
        AggregateIterable<Document> result = collection.aggregate(pipeline);
        List<Document> articles = new ArrayList<>();
        for (Document doc : result) {
            articles.add(doc);
        }
        return articles;
    }

    //g. Get the articles of a given type, together with the name of the newspaper

    //h. Get the number of Sports articles by newspaper

    //i. Get the name of the newspaper with highest number of Sports articles

    //j. Get the articles with no rating lower than 3

    //k. Get the average number of subscriptions per reader

    //l. Number of read articles per reader

    //m. Number of articles with average rating greater than 4

    //n. Readers with no review lower than 3

    //o. Get the articles with a rating lower than 5 of a given newspaper, indicating if the reader

    //has rated more than 4 articles with a lower-than-5 rating

    //p. Readers that are not registered as users (Use $lookup)

}

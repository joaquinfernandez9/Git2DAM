package dao.pattern;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.inject.Inject;
import org.bson.Document;

public class DaoMongo {
    private final MongoCollection<Document> col;
    private final Gson gson;

    @Inject
    public DaoMongo(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017/");
        MongoDatabase db = mongoClient.getDatabase("exam");
        this.col = db.getCollection("examOrders");
    }


}

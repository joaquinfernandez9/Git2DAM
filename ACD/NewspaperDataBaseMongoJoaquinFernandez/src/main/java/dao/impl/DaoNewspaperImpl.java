package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import dao.DaoNewspaper;
import jakarta.inject.Inject;
import model.Newspaper;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
public class DaoNewspaperImpl implements DaoNewspaper {


    private final MongoCollection<Document> col;

    private final Gson gson;

    @Inject
    public DaoNewspaperImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase db = mongoClient.getDatabase("JoaquinFernandez");
        this.col = db.getCollection("nespapers");
    }

    @Override
    public List<Newspaper> getAll() {
        List<Newspaper> newspapers = new ArrayList<>();
        List<Document> result = col.find().into(new ArrayList<>());
        for (Document document : result) {
            ObjectId id = document.getObjectId("_id");
            String name = document.getString("name");
            String relDate = document.getString("relDate");
            Newspaper newspaper = new Newspaper(id, name, relDate);
            newspapers.add(newspaper);
        }
        return newspapers;
    }


    @Override
    public Newspaper get(ObjectId id) {
        try {
            Document doc = col.find(eq("id", id)).first();
            assert doc != null;
            return gson.fromJson(doc.toJson(), Newspaper.class);

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public int add(Newspaper n) {
        try {
            Document doc = Document.parse(gson.toJson(n));
            col.insertOne(doc);
            return 1;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @Override
    public Map<String, Integer> getNbrArticles(ObjectId newspaper) {
        return null;
    }


    @Override
    public int delete(ObjectId id) {
        try {
            col.deleteOne(eq("_id", id));
            return 1;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }


    @Override
    public int update(Newspaper n) {
        int result;
        Document document = col.findOneAndUpdate(eq("_id", n.get_id()),
                Updates.set("name", n.getName()));
        if (document == null) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }
}




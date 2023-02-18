package dao.impl;

import com.google.gson.Gson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dao.DaoReader;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Log4j2
public class DaoReaderImpl implements DaoReader {

    private final MongoCollection<Document> col;

    private final Gson gson;

    @Inject
    public DaoReaderImpl(Gson gson) {
        this.gson = gson;
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase db = mongoClient.getDatabase("JoaquinFernandez");
        this.col = db.getCollection("nespapers", Document.class).withCodecRegistry(pojoCodecRegistry);
    }


    @Override
    public List<Reader> getAll() {
        try {
            List<Reader> readers = new ArrayList<>();
            List<Document> docs = col.find().projection(new Document("readers", 1).append("_id", 0)).into(new ArrayList<>());

            for (Document doc : docs) {
                List<Document> document = doc.get("readers", List.class);
                for (Document doc1 : document) {
                    readers.add(gson.fromJson(doc1.toJson(), Reader.class));
                }
            }
            return readers;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Reader> getAll(Newspaper idNewspaper) {
        try {
            List<Reader> readers = new ArrayList<>();
            List<Document> documents = col.find(Filters.eq("_id", idNewspaper.get_id()))
                    .projection(new Document("readers", 1)
                            .append("_id", 0))
                    .into(new ArrayList<>());

            for (Document doc : documents) {
                List<Document> document = doc.get("readers", List.class);
                for (Document doc1 : document) {
                    readers.add(gson.fromJson(doc1.toJson(), Reader.class));
                }
            }
            return readers;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<Reader> getAll(String description) {
        return null;
    }

    @Override
    public Reader get(int id) {
        Document result = col.find(Filters.eq("id", id)).first();
        if (result == null) {
            return null;
        } else {
            return gson.fromJson(result.toJson(), Reader.class);
        }
    }

    @Override
    public int delete(Newspaper newspaper, Reader reader) {
        Document document = col.find(Filters.eq("_id", newspaper.get_id())).first();
        if (document == null) {
            return -1;
        } else {
            Document result = new Document("id", reader.getId())
                    .append("name", reader.getName())
                    .append("cancellationDate", reader.getCancellationDate());
            Document update = new Document("$pull", new Document("readers", result));
            Document.parse(gson.toJson(reader));
            col.updateOne(document, update);
            return 1;
        }
    }


    @Override
    public int save(Reader reader, Newspaper n) {
        Document doc = col.find(Filters.eq("_id", n.get_id())).first();
        List<Reader> all = getAll();
        if (doc == null) {
            return -1;
        } else {
            Document result = new Document("id", reader.getId())
                    .append("id", all.stream().max(Comparator.comparingInt(Reader::getId)).get().getId() + 1)
                    .append("name", reader.getName())
                    .append("cancellationDate", reader.getCancellationDate());
            Document update = new Document("$push", new Document("readers", result));
            Document.parse(gson.toJson(reader));
            col.updateOne(doc, update);
            return 1;
        }
    }

    @Override
    public int update(Reader reader) {
        Document result = col.findOneAndUpdate(Filters.eq("id", reader.getId()),
                Updates.set("name", reader.getName()));
        if (result == null) {
            return -1;
        } else {
            return 1;
        }
    }


}

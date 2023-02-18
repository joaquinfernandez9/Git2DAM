package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import dao.DaoArticle;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Article;
import lombok.extern.log4j.Log4j2;
import model.Newspaper;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
public class DaoArticleImpl implements DaoArticle {

    private final MongoCollection<Document> col;

    private final Gson gson;


    @Inject
    public DaoArticleImpl(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://informatica.iesquevedo.es:2323");
        MongoDatabase db = mongoClient.getDatabase("JoaquinFernandez");
        this.col = db.getCollection("nespapers");

    }

    @Override
    public List<Article> getAll() {
        List<Article> articleList = new ArrayList<>();
        try {
            List<Document> documents = col.find()
                    .projection(new Document("articles.readArticles", 0)
                            .append("_id", 0))
                    .into(new ArrayList<>());
            for (Document document : documents) {
                List<Document> doc = document.get("articles", List.class);
                for (Document document1 : doc) {
                    articleList.add(gson.fromJson(document1.toJson(), Article.class));
                }
            }
            return articleList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    @Override
    public List<Article> getAll(ObjectId id_newspaper){
        try {
            List<Article> articleList = new ArrayList<>();
            List<Document> doc = new ArrayList<>();
            List<Document> documents = col.find(Filters.eq("_id", id_newspaper))
                    .projection(new Document("articles.readArticles", 0)
                            .append("_id", 0))
                    .into(new ArrayList<>());
            for (Document document : documents) {
                doc = document.get("articles", List.class);
            }
            for (Document document : doc) {
                articleList.add(gson.fromJson(document.toJson(), Article.class));
            }
            return articleList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //get all articles of a newspaper
    public List<Article> getAll(String newspaper) {
        List<Article> articles = new ArrayList<>();
        List<Document> doc = new ArrayList<>();
        List<Document> doc2 = col.find(eq("name", newspaper))
                .projection(new Document("articles.readARticles", 0)
                        .append("_id", 0))
                .into(new ArrayList<>());

        for (Document document : doc2) {
            doc = document.get("articles", List.class);
        }
        for (Document document : doc) {
            Article article = gson.fromJson(document.toJson(), Article.class);
            articles.add(article);
        }
        return articles;

    }


    @Override
    public Article get(Article article, Newspaper newspaper) {
        Document document = col.find().first();
        if (document == null) {
            return null;
        }
        Article foundArticle = gson.fromJson(document.toJson(), Article.class);
        foundArticle.setName(document.getString("name"));
        return foundArticle;

    }


    @Override
    public int save(Article a, Newspaper newspaper) {
        Document document = col.find(Filters.eq("_id", newspaper.get_id())).first();
        List<Article> articles = getAll();
        if (document == null) {
            return -1;
        } else {
            for (Article article1 : articles) {
                if (article1.getType().equals(a.getType())) {
                    return -1;
                }
            }
            Document result = new Document("name", a.getName())
                    .append("type", a.getType())
                    .append("readArticles", new ArrayList<>());
            Document.parse(gson.toJson(a));
            col.updateOne(document, Updates.push("articles", result));
            return 1;
        }

    }

    @Override
    public int delete(Article article, Newspaper newspaper){
        Document document = col.find(Filters.eq("_id", newspaper.get_id())).first();
        if (document == null) {
            return -1;
        }else {
            Document result = new Document("name", article.getName())
                    .append("type", article.getType());
            Document.parse(gson.toJson(article));
            col.updateOne(document, Updates.pull("articles", result));
            return 1;
        }
    }

    @Override
    public List<Article> delete(ObjectId id_newspaper) {
        Document newspaper = col.find(Filters.eq("_id", id_newspaper)).first();
        if (newspaper == null) {
            return null;
        }else {
            Document update = new Document("$set", new Document("articles", new ArrayList<>()));
            col.updateOne(newspaper, update);
            return gson.fromJson(newspaper.toJson(), Newspaper.class).getArticles();
        }

    }


    @Override
    public int update(Article a, Newspaper newspaper, String desc) {
        Document document = col.find(Filters.eq("_id", newspaper.get_id())).first();
        if (document == null) {
            return -1;
        }
        Bson update = Updates.set("articles.$[elem].desc", a.getType());
        UpdateOptions options = new UpdateOptions().arrayFilters(
                Arrays.asList(Filters.and(Filters.eq("elem.desc", desc), Filters.eq("elem.type", a.getType())))
        );
        UpdateResult result = col.updateOne(document, update, options);
        if (result.getModifiedCount() == 1) {
            return 1;
        } else {
            return -1;
        }
    }

}

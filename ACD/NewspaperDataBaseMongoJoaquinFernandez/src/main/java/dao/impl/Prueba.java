package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.DaoArticle;
import model.Article;
import org.bson.Document;

import java.util.List;

public class Prueba {




    public static void main(String[] args) {

        Gson gson = new Gson();

        DaoArticle daoArticle = new DaoArticleImpl(gson);

        List<Article> articles = daoArticle.getAll();

        System.out.println(articles);



    }
}

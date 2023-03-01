package dao;

import com.google.gson.Gson;
import com.mongodb.client.*;
import jakarta.inject.Inject;
import org.bson.BsonNull;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoMongoAggregations {

    private final MongoCollection<Document> col;
    private final Gson gson;

    @Inject
    public DaoMongoAggregations(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017/");
        MongoDatabase db = mongoClient.getDatabase("Population");
        this.col = db.getCollection("employees");
    }


    public List<Document> query1(String name) {
        List<Document> docs = List.of(new Document("$match",
                new Document("Name", name)));
        AggregateIterable<Document> output = col.aggregate(docs);
        List<Document> persons = new ArrayList<>();
        for (Document doc : output) {
            persons.add(doc);
        }
        return persons;
    }

    //get all hobbies
    public List<Document> query2() {
        List<Document> docs = List.of(new Document("$project",
                new Document("_id", 0L)
                        .append("Hobbies", 1L)));
        AggregateIterable<Document> output = col.aggregate(docs);
        List<Document> hobbies = new ArrayList<>();
        for (Document doc : output) {
            hobbies.add(doc);
        }
        return hobbies;
    }

    // cantidad de hobbies de cada persona
    public List<Document> query3(){
        List<Document> docs = List.of(new Document("$project",
                new Document("_id", 0L)
                        .append("Name", 1L)
                        .append("Hobbies", 1L)
                        .append("cantidad_hobbies",
                                new Document("$size",
                                        new Document("$ifNull", Arrays.asList("$Hobbies", Arrays.asList()))))));
        AggregateIterable<Document> output = col.aggregate(docs);
        List<Document> hobbies = new ArrayList<>();
        for (Document doc : output) {
            hobbies.add(doc);
        }
        return hobbies;
    }

    //average hobbies
    public Double query4(){
        List<Document> docs = List.of(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Hobbies", 1L)
                                .append("cantidad_hobbies",
                                        new Document("$size",
                                                new Document("$ifNull", Arrays.asList("$Hobbies", Arrays.asList()))))),
                new Document("$group",
                        new Document("_id",
                                new BsonNull())
                                .append("media_hobbies",
                                        new Document("$avg", "$cantidad_hobbies"))));

        AggregateIterable<Document> output = col.aggregate(docs);
        List<Document> hobbies = new ArrayList<>();
        for (Document doc : output) {
            hobbies.add(doc);
        }
        return hobbies.get(0).getDouble("media_hobbies");
    }

    //get persons that have cycling as hobbie
    public List<Document> query5(String hobbie){
        List<Document> doc = List.of(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Hobbies", 1L)),
                new Document("$match",
                        new Document("Hobbies", hobbie)));
        AggregateIterable<Document> output = col.aggregate(doc);
        List<Document> response = new ArrayList<>();
        for (Document doc2: output){
            response.add(doc2);
        }
        return response;
    }

    // get all the persons older than 25 years old
    public List<Document> query6(){
        List<Document> docs = List.of(new Document("$match",
                new Document("Age",
                        new Document("$gt", 25L))));
        AggregateIterable<Document> output = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: output){
            response.add(doc);
        }
        return response;
    }

    //get everyone who's friends with x
    public List<Document> query7(String friendName){
        List<Document> response = new ArrayList<>();
        List<Document> docs = List.of(new Document("$match",
                new Document("Friends.Name", friendName)));
        AggregateIterable<Document> out = col.aggregate(docs);
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }

    //Obtener la cantidad de documentos en tu colección.
    public int query8(){
        List<Document> docs = List.of(new Document("$count", "Personas"));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response.size();
    }

    //Obtener los nombres y apellidos de las personas en la colección.
    public List<Document> query9(){
        List<Document> docs = List.of(new Document("$project",
                new Document("_id", 0L)
                        .append("Name", 1L)
                        .append("LastName", 1L)));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener la cantidad de amigos que tienen todas las personas en la colección.
    public List<Document> query10(){
        List<Document> docs = List.of();
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener el nombre y la edad de la persona más joven en la colección.
    public List<Document> query11(){
        List<Document> docs = List.of(new Document("$group",
                        new Document("_id",
                                new BsonNull())
                                .append("youngest",
                                        new Document("$min",
                                                new Document("Age", "$Age")
                                                        .append("Name", "$Name")))),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", "$youngest.Name")
                                .append("Age", "$youngest.Age")));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener los nombres de las personas que tienen más de 1 hobby.
    public List<Document> query12(){
        List<Document> docs = List.of(new Document("$project",
                        new Document("Name", 1L)
                                .append("Hobbies", 1L)
                                .append("cantidad_hobbies",
                                        new Document("$size",
                                                new Document("$ifNull", Arrays.asList("$Hobbies", List.of()))))),
                new Document("$match",
                        new Document("cantidad_hobbies",
                                new Document("$gt", 1L))));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener los nombres de las personas que tienen al menos un amigo mayor de 25 años.
    public List<Document> query13(){
        List<Document> docs = Arrays.asList(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Friends", 1L)),
                new Document("$unwind",
                        new Document("path", "$Friends")),
                new Document("$match",
                        new Document("Friends.Age",
                                new Document("$gt", 25L))));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener los nombres de las personas que tienen como hobby el ciclismo. hecho arriba

    //Obtener los nombres de las personas que tienen más de 2 hobbies y además tienen amigos.
    public List<Document> query15(){
        List<Document> docs = Arrays.asList(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Friends",
                                        new Document("$cond",
                                                new Document("if",
                                                        new Document("$isArray", "$Friends"))
                                                        .append("then", "$Friends")
                                                        .append("else", Arrays.asList("$Friends"))))
                                .append("Hobbies", 1L)
                                .append("cantidad_hobbies",
                                        new Document("$size",
                                                new Document("$ifNull", Arrays.asList("$Hobbies", Arrays.asList()))))),
                new Document("$match",
                        new Document("cantidad_hobbies",
                                new Document("$gt", 1L))),
                new Document("$match",
                        new Document("Friends.Age",
                                new Document("$gt", 25L))));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener el promedio de edad de las personas que tienen como hobby el running.
    public List<Document> query16(){
        List<Document> docs = Arrays.asList(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Age", 1L)
                                .append("Hobbies", 1L)),
                new Document("$match",
                        new Document("Hobbies", "running")),
                new Document("$group",
                        new Document("_id",
                                new BsonNull())
                                .append("avg_age",
                                        new Document("$avg", "$Age"))));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }
    //Obtener los nombres de las personas que tienen amigos mayores de 30 años y además tienen más de 2 hobbies.
    public List<Document> query17(){
        List<Document> docs = Arrays.asList(new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", 1L)
                                .append("Hobbies",
                                        new Document("$cond",
                                                new Document("if",
                                                        new Document("$isArray", "$Hobbies"))
                                                        .append("then", "$Hobbies")
                                                        .append("else", Arrays.asList("$Hobbies"))))
                                .append("Friends", 1L)),
                new Document("$unwind",
                        new Document("path", "$Friends")),
                new Document("$match",
                        new Document("Friends.Age",
                                new Document("$gt", 25L))),
                new Document("$group",
                        new Document("_id", "$Name")
                                .append("friends",
                                        new Document("$push", "$Friends.Name"))),
                new Document("$match",
                        new Document("friends",
                                new Document("$exists", true)
                                        .append("$ne", Arrays.asList()))),
                new Document("$project",
                        new Document("_id", 0L)
                                .append("Name", "$_id")
                                .append("Friends", "$friends")));
        AggregateIterable<Document> out = col.aggregate(docs);
        List<Document> response = new ArrayList<>();
        for (Document doc: out){
            response.add(doc);
        }
        return response;
    }

}

package dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import jakarta.inject.Inject;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;

import java.util.ArrayList;
import java.util.List;

public class DaoMongoEmployees {

    private final MongoCollection<Document> col;
    private final Gson gson;

    @Inject
    public DaoMongoEmployees(Gson gson) {
        this.gson = gson;
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017/");
        MongoDatabase db = mongoClient.getDatabase("Population");
        this.col = db.getCollection("employees");
    }

    //get all persons
    public List<Person> getAll() {
        List<Person> response = new ArrayList<>();
        List<Document> doc2 = col.find().into(new ArrayList<>());
        for (Document doc3 : doc2) {
            ObjectId id = doc3.getObjectId("_id");
            String name = doc3.getString("Name");
            String lastName = doc3.getString("LastName");
            int age = doc3.getInteger("Age");
            Person person = new Person(id, name, lastName, age);
            List<Person> friendsList = new ArrayList<>();
            person.setFriends(friendsList);
            List<String> hobbiesList = new ArrayList<>();
            person.setHobbies(hobbiesList);
            Object friendsObj = doc3.get("Friends");
            if (friendsObj instanceof List) {
                List<Document> friends = (List<Document>) friendsObj;
                for (Document friend : friends) {
                    if (friend instanceof Document) {
                        String friendName = (friend).getString("Name");
                        int friendAge = (friend).getInteger("Age");
                        person.getFriends().add(new Person(friendName, friendAge));
                    }
                }
            }
            List<String> hobbies = (List<String>) doc3.get("Hobbies");
            if (hobbies != null) {
                for (String hobby : hobbies) {
                    person.getHobbies().add(hobby);
                }
            }
            response.add(person);
        }
        return response;
    }

    //add person
    public int add(Person person) {
        try {
            Document doc = Document.parse(gson.toJson(person));
            col.insertOne(doc);
            person.setName(doc.getString("Name"));
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // add friend
    public int addFriend(String name, Person friend) {
        int response = 1;
        try {
            Document doc = Document.parse(gson.toJson(friend));
            col.updateOne(eq("Name", name), addToSet("Friends", doc));
        } catch (Exception e) {
            response = 0;
            e.printStackTrace();
        }
        return response;
    }


    //delete friend
    public int deleteFriend(Person friend) {
        int response = 1;
        String eppe = "pepe";
        try {
            col.updateOne(eq("Name", eppe), Updates.pull("Friends", Filters.eq("Name", friend.getName())));
        } catch (Exception e) {
            response = 0;
            e.printStackTrace();
        }
        return response;
    }

    public int addHobbie(Person person, String hobbie) {
        int response = 1;
        try {
            col.updateOne(eq("Name", person.getName()), addToSet("Hobbies", hobbie));
        } catch (Exception e) {
            response = 0;
            e.printStackTrace();
        }
        return response;
    }


}


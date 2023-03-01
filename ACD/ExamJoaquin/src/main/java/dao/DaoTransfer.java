package dao;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.JPAUtil;
import dao.hibernateModel.Friend;
import dao.hibernateModel.Hobbies;
import dao.hibernateModel.PersonHibernate;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

public class DaoTransfer {

    //mongo 4. mongo: pasar datos de mongo a hibernate

    private final MongoCollection<Document> col;
    private final Gson gson;
    private final JPAUtil jpautil;
    private EntityManager em;

    @Inject
    public DaoTransfer(Gson gson, JPAUtil jpautil) {
        this.gson = gson;
        this.jpautil = jpautil;
        MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017/");
        MongoDatabase db = mongoClient.getDatabase("Population");
        this.col = db.getCollection("employees");
    }

    //get all llamando de mongo paara tener una lista con todas las personas
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

    /*
    a partir de esa lista hacer los updates a la base de datos llamada employees,
    tener en cuenta que los unicos datos que tienen son:
        - Person: id, name, lastName y age
        - Friend: name, age y person_id
        - Hobbies: hobbie, person_id
     */

    public int transfer(Person person) {
        int response = 1;
        em = jpautil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            //pasamos los datos de la person que hemos recibido al formato necesario
            PersonHibernate insertPerson = new PersonHibernate(person.get_id().toHexString(),
                    person.getName(), person.getLastName(), person.getAge());
            List<Hobbies> hobbies = new ArrayList<>(Collections.emptyList());
            person.getHobbies().stream().forEach(hobbie -> {
                Hobbies hobbie2 = new Hobbies(hobbie);
                hobbie2.setPerson(insertPerson);
                hobbies.add(hobbie2);
            });
            List<Friend> friends = new ArrayList<>(Collections.emptyList());
            person.getFriends().stream().forEach(friend -> {
                Friend newFriend = new Friend(friend.getName(), friend.getAge());
                newFriend.setPerson(insertPerson);
                friends.add(newFriend);
            });
            //una vez con la persona que queremos insertar y las listas tanto de hobbies como de amigos
            //hacemos transaction por que vamos a trabajar con mas de una tabla
            tx.begin();
            em.persist(insertPerson);
            //añadirmos los hobbies
            for (Hobbies hobbie : hobbies) {
                em.persist(hobbie);
            }
            //añadimos los friends
            for (Friend friend : friends) {
                em.persist(friend);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            response = 0;
            tx.rollback();
        }
        return response;
    }

}

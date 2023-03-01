package dao;

import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
public class Person {
    private ObjectId _id;
    private String Name;
    private String LastName;
    private int Age;
    private List<String> Hobbies;
    private List<Person> Friends;

    public Person(ObjectId _id, String name, int age) {
        this._id = _id;
        Name = name;
        this.Age = age;
    }

    public Person(ObjectId _id) {
        this._id = _id;
    }

    public Person(String name, int age) {
        Name = name;
        this.Age = age;
    }

    public Person(ObjectId _id, String name, String lastName, int age) {
        this._id = _id;
        Name = name;
        LastName = lastName;
        this.Age = age;
    }
}

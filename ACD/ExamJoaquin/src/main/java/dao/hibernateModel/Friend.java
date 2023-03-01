package dao.hibernateModel;

import dao.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Friends")
public class Friend {
    @Id
    public String name;
    public int age;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    private PersonHibernate person;

    public Friend(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

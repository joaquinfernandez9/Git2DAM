package dao.hibernateModel;

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
@Table(name = "Hobbies")
public class Hobbies {
    @Id
    public String hobbie;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public PersonHibernate person;

    public Hobbies(String hobbie) {
        this.hobbie = hobbie;
    }
}

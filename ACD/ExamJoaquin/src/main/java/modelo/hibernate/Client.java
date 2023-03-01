package modelo.hibernate;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "balance")
    public double balance;

    public Client(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(int id) {
        this.id = id;
    }
}

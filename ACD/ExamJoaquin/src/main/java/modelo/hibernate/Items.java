package modelo.hibernate;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "price")
    public double price;

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<PurchaseItem> purchaseItem;

    public Items(String name) {
        this.name = name;
    }


    public Items(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Items(int i, String item1, double v) {
        this.id = i;
        this.name = item1;
        this.price = v;
    }
}

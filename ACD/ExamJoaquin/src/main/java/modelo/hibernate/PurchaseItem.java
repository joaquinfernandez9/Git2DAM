package modelo.hibernate;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "purchases_items")
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "amount")
    public int amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_purchase", referencedColumnName = "id", nullable = false)
    public Purchase purchase;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    public Items item;

    public PurchaseItem(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public PurchaseItem(int id, Items item) {
        this.id = id;
        this.item = item;
    }

    public PurchaseItem(int id, int amount, Items item) {
        this.id = id;
        this.amount = amount;
        this.item = item;
    }
}

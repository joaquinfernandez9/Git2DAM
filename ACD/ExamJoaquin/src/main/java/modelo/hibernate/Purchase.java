package modelo.hibernate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "p_date")
    public LocalDate date;

    @Column(name = "total_cost")
    public double totalCost;

    @Column(name = "paid")
    public int paid;

    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Collection<PurchaseItem> purchaseItems;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_client", referencedColumnName = "id", nullable = false)
    private Client client;

    public Purchase(LocalDate date, Collection<PurchaseItem> purchaseItems, Client client) {
        this.date = date;
        this.purchaseItems = purchaseItems;
        this.client = client;
    }

    public Purchase(int id, LocalDate date, double totalCost, int paid, Client client, Collection<PurchaseItem> purchaseItems) {
        this.id = id;
        this.date = date;
        this.totalCost = totalCost;
        this.paid = paid;
        this.client = client;
        this.purchaseItems = purchaseItems;
    }
}

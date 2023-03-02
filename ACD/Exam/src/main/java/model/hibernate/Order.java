package model.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;


    @ManyToOne
    @JoinColumn(name = "table_id")
    public Table table;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @Column(name = "order_date")
    public LocalDate orderDate;

    @Column(name = "total")
    public double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Collection<OrderItems> orderItems;


    public Order(int id, Table table, Customer customer, LocalDate orderDate, double total) {
        this.id = id;
        this.table = table;
        this.customer = customer;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Order(int id, Table table, Customer customer, LocalDate orderDate, double total, Collection<OrderItems> orderItems) {
        this.id = id;
        this.table = table;
        this.customer = customer;
        this.orderDate = orderDate;
        this.total = total;
        this.orderItems = orderItems;
    }

    public Order(int id, Table table, Customer customer, LocalDate orderDate) {
        this.id = id;
        this.table = table;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", table=" + table +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", orderItems=" + orderItems +
                '}';
    }
}

package model.hibernate;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    public MenuItem menuItem;
    @Column(name = "quantity")
    public int quantity;

    @Column(name = "price")
    public double price;

    public OrderItems(int id, Order order, MenuItem menuItem, int quantity, double price) {
        this.id = id;
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItems(Order order, MenuItem menuItem, int quantity, double price) {
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", order=" + order +
                ", menuItem=" + menuItem +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

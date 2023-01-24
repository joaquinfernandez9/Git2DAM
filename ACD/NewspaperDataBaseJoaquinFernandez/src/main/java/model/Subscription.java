package model;

import jakarta.persistence.*;
import lombok.Data;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "subscription")
@NamedQuery(name = "GET_ALL_SUBSCRIPTIONS",
        query = "from Subscription where id_newspaper=:idNewspaper and cancellation_date is not null")
@NamedQuery(name = "GET_ALL_SUBSCRIPTIONS_READER",
        query = "from Subscription where id_reader=:idReader and cancellation_date is null")
@NamedQuery(name = "DELETE_SUBSCRIPTION_READER",
        query = "delete from Subscription where id_reader=:idReader")

public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reader;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_newspaper;

    @Basic
    @Column(name = "sing_date")
    private LocalDate sing_date;

    @Basic
    @Column(name = "cancellation_date")
    private LocalDate cancellation_date;

    @ManyToOne
    @JoinColumn(name = "id_reader", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Reader reader;

    public Subscription(int id_reader, int id_newspaper, LocalDate sing_date, LocalDate cancellation_date) {
        this.id_reader = id_reader;
        this.id_newspaper = id_newspaper;
        this.sing_date = sing_date;
        this.cancellation_date = cancellation_date;
    }
}
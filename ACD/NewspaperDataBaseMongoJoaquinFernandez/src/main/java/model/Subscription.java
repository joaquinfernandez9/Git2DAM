package model;

import jakarta.persistence.*;
import lombok.Data;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Data
public class Subscription {
    private int id_reader;
    private ObjectId id_newspaper;
    private LocalDate sing_date;
    private LocalDate cancellation_date;
    private Reader reader;

    public Subscription(int id_reader, ObjectId id_newspaper, LocalDate sing_date, LocalDate cancellation_date) {
        this.id_reader = id_reader;
        this.id_newspaper = id_newspaper;
        this.sing_date = sing_date;
        this.cancellation_date = cancellation_date;
    }
}
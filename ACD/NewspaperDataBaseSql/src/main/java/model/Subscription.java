package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Subscription {
    private int id_reader;
    private int id_newspaper;
    private LocalDate sing_date;
    private LocalDate cancellation_date;

    public Subscription(int id_reader, int id_newspaper,
                        LocalDate sing_date, LocalDate cancellation_date) {
        this.id_reader = id_reader;
        this.id_newspaper = id_newspaper;
        this.sing_date = sing_date;
        this.cancellation_date = cancellation_date;
    }

    public Subscription(int id_reader, int id_newspaper, LocalDate sing_date) {
        this.id_reader = id_reader;
        this.id_newspaper = id_newspaper;
        this.sing_date = sing_date;
    }
}
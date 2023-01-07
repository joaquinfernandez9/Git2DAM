package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "login")
public class Subscription {
    @Id
    private int id_reader;
    @Column
    private int id_newspaper;
    @Column
    private LocalDate sing_date;
    @Column
    private LocalDate cancellation_date;


}
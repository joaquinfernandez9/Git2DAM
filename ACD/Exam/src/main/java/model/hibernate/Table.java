package model.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@jakarta.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "table_number")
    public int tableNum;

    @Column(name = "number_of_seats")
    public int numSeats;

    public Table(int id, int tableNum, int numSeats) {
        this.id = id;
        this.tableNum = tableNum;
        this.numSeats = numSeats;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", tableNum=" + tableNum +
                ", numSeats=" + numSeats +
                '}';
    }
}

package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reader")
@NamedQueries({@NamedQuery(name = "GET_ALL_READERS",
        query = "from Reader")})
public class Reader {
    @Id
    private int id;
    @Column(name = "name_reader")
    private String name_reader;
    @Column(name = "birth_reader")
    private LocalDate birth_reader;
    @OneToOne(mappedBy="reader", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Login login;


}




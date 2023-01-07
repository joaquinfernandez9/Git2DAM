package model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "login")
public class Login {
    @Id
    private String user;
    @Column(name = "password")
    private String password;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_reader", referencedColumnName = "id", nullable = false)
    private Reader reader;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }
}

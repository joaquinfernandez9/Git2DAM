package com.example.graphqlserver.data.modelo;

import com.example.graphqlserver.domain.modelo.Rueda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "coche")

public class CocheEntity {
    @Id
    private String id;
    private String marca;

    @OneToMany(mappedBy = "coche", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<RuedaEntity> ruedas;
}

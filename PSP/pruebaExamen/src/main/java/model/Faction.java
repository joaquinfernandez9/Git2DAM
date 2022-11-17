package model;

import dao.localDateAdapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.time.LocalDate;

@XmlRootElement
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Faction {
    @XmlElement
    private String name;
    @XmlElement
    private String contact;
    @XmlElement
    private String planet;
    @XmlElement
    private int numberCS;
    @XmlElement
    private LocalDate dateLastPurchase;
    @XmlElement
    private Weapons weapons;

    public Faction() {
    }

}

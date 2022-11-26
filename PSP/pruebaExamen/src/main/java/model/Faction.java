package model;

import dao.localDateAdapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import java.time.LocalDate;

@Data
@XmlRootElement
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
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateLastPurchase;
    @XmlElement
    private Weapons weapons;

    public Faction(String name, String contact, String planet,
                   int numberCS, LocalDate dateLastPurchase) {
        this.name = name;
        this.contact = contact;
        this.planet = planet;
        this.numberCS = numberCS;
        this.dateLastPurchase = dateLastPurchase;
    }

    public Faction() {
    }
}

package model;


import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapons {
    @XmlList
    private List<Weapon> weapon;

    public Weapons() {
    }
}

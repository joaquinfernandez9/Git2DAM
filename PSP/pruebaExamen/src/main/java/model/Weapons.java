package model;


import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "weapons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapons {
    @XmlElement(name = "weapon")
    private List<Weapon> weapon;

}

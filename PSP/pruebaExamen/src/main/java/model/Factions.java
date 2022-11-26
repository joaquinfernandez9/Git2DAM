package model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Factions {
    @XmlElement
    private List<Faction> faction;

    public Factions() {
        faction=new ArrayList<>();
    }

    public Factions(List<Faction> faction) {
        this.faction = faction;
    }
}


package model;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Factions {
    @XmlList
    private List<Faction> faction;

    public Factions() {
        this.faction = new ArrayList<>();
    }
}


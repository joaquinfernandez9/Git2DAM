package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "weapon")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weapon {
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private double price;

    //auto id
    public Weapon(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Weapon(int id, double price) {
        this.id = id;
        this.price = price;
    }
}

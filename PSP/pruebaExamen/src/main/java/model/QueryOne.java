package model;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
public class QueryOne {
    private int id;
    private String wname;
    private double wprice;
    private String fname;
}

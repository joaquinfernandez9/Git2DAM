package domain.modelo;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "readers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Readers {
    @XmlElement(name = "reader")
    private List<Reader> readers;

}

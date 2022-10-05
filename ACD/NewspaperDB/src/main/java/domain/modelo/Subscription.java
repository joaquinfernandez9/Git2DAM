package domain.modelo;

import dao.localDate.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.LocalDate;

@Data
@XmlRootElement(name = "subscription")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscription {
    @XmlElement
    private int idSubscription;
    @XmlElement
    private int idReader;
    @XmlElement
    private int idNewspaper;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate singDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate cancellationDate;

    public Subscription(int idSubscription, int idReader, int idNewspaper, LocalDate singDate, LocalDate cancellationDate) {
        this.idSubscription = idSubscription;
        this.idReader = idReader;
        this.idNewspaper = idNewspaper;
        this.singDate = singDate;
        this.cancellationDate = cancellationDate;
    }

    public Subscription() {

    }
}

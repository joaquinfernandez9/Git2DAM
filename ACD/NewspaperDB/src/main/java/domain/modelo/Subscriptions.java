package domain.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "subscriptions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subscriptions {
    @XmlElement(name = "subscription")
    private List<Subscription> subscriptionsList;

}

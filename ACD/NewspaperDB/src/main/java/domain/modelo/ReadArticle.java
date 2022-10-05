package domain.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "readArticle")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadArticle {
    @XmlElement
    private int idReadArticle;
    @XmlElement
    private int idReader;
    @XmlElement
    private int idArticle;
    @XmlElement
    private int rating;

    public ReadArticle(int idReadArticle, int idReader, int idArticle, int rating) {
        this.idReadArticle = idReadArticle;
        this.idReader = idReader;
        this.idArticle = idArticle;
        this.rating = rating;
    }

    public ReadArticle() {

    }
}

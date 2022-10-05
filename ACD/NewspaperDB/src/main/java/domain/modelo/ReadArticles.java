package domain.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "readArticles")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReadArticles {
    @XmlElement(name = "readArticle")
    private List<ReadArticle> readArticlesList;
}

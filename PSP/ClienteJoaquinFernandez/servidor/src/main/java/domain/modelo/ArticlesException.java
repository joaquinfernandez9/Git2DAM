package domain.modelo;

import lombok.Data;

import java.util.List;

@Data
public class ArticlesException extends RuntimeException {

    private final List<Integer> articles;

    public ArticlesException(String message, List<Integer> articles) {
        super(message);
        this.articles = articles;
    }

}


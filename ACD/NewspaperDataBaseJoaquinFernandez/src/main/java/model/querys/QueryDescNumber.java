package model.querys;

import lombok.Data;

@Data
public class QueryDescNumber {

    private String ArticleName;
    private String description;
    private int readers;

    public QueryDescNumber(String articleName, String description, int readers) {
        ArticleName = articleName;
        this.description = description;
        this.readers = readers;
    }
}

package model;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
public class Newspaper {
    private int newspaperID;
    private String newspaperName;
    private LocalDate releaseDate;



}

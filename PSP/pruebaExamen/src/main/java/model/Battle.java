package model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Battle {
    private int id;
    private String bname;
    private String faction_one;
    private String faction_two;
    private String bplace;
    private LocalDate bdate;
    private int id_spy;

    public Battle(int id, String bname, String faction_one, String faction_two, String bplace, LocalDate bdate, int id_spy) {
        this.id = id;
        this.bname = bname;
        this.faction_one = faction_one;
        this.faction_two = faction_two;
        this.bplace = bplace;
        this.bdate = bdate;
        this.id_spy = id_spy;
    }

    public Battle() {
    }
}

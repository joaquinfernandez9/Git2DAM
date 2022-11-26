package model;

import lombok.Data;

@Data
public class Spy {
    private int id;
    private String sname;
    private String srace;

    public Spy(int id, String sname, String srace) {
        this.id = id;
        this.sname = sname;
        this.srace = srace;
    }

    public Spy() {
    }
}

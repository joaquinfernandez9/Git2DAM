package model;

import lombok.Data;

import java.sql.Time;

@Data
public class Code {
    private String generated_code;
    private Time expiration_date;
    private String user_code;

    public Code() {
    }


    public Code(String generated_code, Time expiration_date, String user_code) {
        this.generated_code = generated_code;
        this.expiration_date = expiration_date;
        this.user_code = user_code;
    }



}

package model;


import lombok.Data;

@Data
public class Login {
    private String user;
    private String password;
    private String email;
    private int id_reader;
    private int active;



    public Login(String user, String password, String email, int id_reader) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.id_reader = id_reader;
    }

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Login() {
    }
}

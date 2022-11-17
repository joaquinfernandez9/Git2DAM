package model;


import lombok.Data;

@Data
public class Login {
    private String user;
    private String password;

    public Login(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Login() {
    }
}

package domain;

import lombok.Data;

@Data
public class Vault {
    private int id;
    private String name;
    private int permission;
    private String password;
    private String username;
}

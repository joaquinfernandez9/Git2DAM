package dao;

import config.ConfigYaml;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class DaoLogin {

    public boolean login(String userName, String password){
        String name = String.valueOf(Paths.get(ConfigYaml
                .getInstance().getProperty("user")));
        String pass = String.valueOf(Paths.get(ConfigYaml
                .getInstance().getProperty("password")));
        return userName.equals(name) &&
                password.equals(pass);
    }

}

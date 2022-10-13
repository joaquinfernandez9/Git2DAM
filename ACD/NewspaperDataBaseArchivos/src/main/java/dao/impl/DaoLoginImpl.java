package dao.impl;

import config.ConfigYaml;
import dao.DaoLogin;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Paths;

@Log4j2
public class DaoLoginImpl implements DaoLogin {

    @Override public boolean login(String userName, String password){
        String name = String.valueOf(Paths.get(ConfigYaml
                .getInstance().getProperty("user")));
        String pass = String.valueOf(Paths.get(ConfigYaml
                .getInstance().getProperty("password")));
        return userName.equals(name) &&
                password.equals(pass);
    }

}

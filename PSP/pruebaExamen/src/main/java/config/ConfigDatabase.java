package config;

import jakarta.inject.Singleton;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Singleton
public class ConfigDatabase {
    private Properties p;

    public ConfigDatabase(){
        Path path = Paths.get("src/main/resources/mysql-properties.xml");
        p = new Properties();
        InputStream stream;
        try {
            stream = Files.newInputStream(path);
            p.loadFromXML(stream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String clave) {
        return p.getProperty(clave);
    }

}

package config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuracion {

    private String pathUsuarios;
    private String pathPeliculas;
    private String pathSeries;

    public Configuracion(){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config.yaml"));
            this.pathUsuarios = p.getProperty("pathUsuarios");
            this.pathPeliculas = p.getProperty("pathPeliculas");
            this.pathSeries = p.getProperty("pathSeries");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

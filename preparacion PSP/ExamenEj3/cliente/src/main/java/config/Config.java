package config;

import jakarta.inject.Singleton;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Singleton
@Getter
public class Config {

    private Properties p;

    public Config() {

    }
}

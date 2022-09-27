package dao;

import config.ConfigProperties;
import config.ConfigYaml;
import domain.modelo.Newspaper;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoNewspaper {


    public List<Newspaper> getAll() {
        // get path from config
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("pathNewspaper"));
        List<Newspaper> newspaperList = new ArrayList<>();

        try {
            List<String> newspapers = Files.readAllLines(file);
            newspapers.forEach(np -> newspaperList.add(new Newspaper(np)));

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return newspaperList;

    }

    public boolean deleteNewspaper(int news) {
        Path file = Paths.get(ConfigProperties.getInstance().getProperty("pathNewspaper"));
        try {
            List<String> newspapers = Files.readAllLines(file);
            newspapers.remove(news-2);
            Files.write(file, newspapers);
            return true;

        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

}

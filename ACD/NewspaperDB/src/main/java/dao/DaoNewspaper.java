package dao;

import config.ConfigProperties;
import config.ConfigYaml;
import domain.modelo.Newspaper;
import jakarta.enterprise.inject.New;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DaoNewspaper {


    public List<Newspaper> getAll() {
        Path file = Paths.get(ConfigProperties
                .getInstance().getProperty("pathNewspaper"));
        List<Newspaper> newspaperList = new ArrayList<>();
        try {
            List<String> newspapers = Files.readAllLines(file);
            newspapers.forEach(np ->
                    newspaperList.add(new Newspaper(np)));

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return newspaperList;
    }

    public boolean deleteNewspaper(int id) {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathNewspaper"));
        List<Newspaper> newspaperList = getAll();
        try {
            List<String> newspapers = Files.readAllLines(file);
            Newspaper n = newspaperList.stream().filter(newspaper ->
                    newspaper.getNewspaperID() == id)
                    .findFirst().orElse(null);
            newspaperList.remove(n);
            if (n!=null){
                newspapers.remove(n.toStringTextFile());
                Files.write(file, newspapers);
                return true;
            } else return false;

        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }
    // get getAll save delete update

}

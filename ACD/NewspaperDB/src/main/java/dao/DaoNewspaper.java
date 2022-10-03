package dao;

import config.ConfigProperties;
import domain.modelo.Newspaper;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    public void deleteNewspaper(int id) {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathNewspaper"));
        List<Newspaper> newspaperList = getAll();
        try {
            List<String> newspapers = Files.readAllLines(file);
            Newspaper n = newspaperList.stream().filter(newspaper ->
                    newspaper.getNewspaperID() == id)
                    .findFirst().orElse(null);
            if (n!=null){
                newspaperList.remove(n);
                newspapers.remove(n.toStringTextFile());
                Files.write(file, newspapers);

            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    // get getAll save delete update

}

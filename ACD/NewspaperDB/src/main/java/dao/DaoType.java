package dao;

import config.ConfigProperties;
import domain.modelo.Article;
import domain.modelo.ArticleType;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DaoType {

    public List<ArticleType> getAll(){
        Path file= Paths.get(ConfigProperties.getInstance().getProperty("pathType"));
        List<ArticleType> articlesList = new ArrayList<>();

        try{
            List<String> articles = Files.readAllLines(file);
            articles.forEach(article -> articlesList.add(new ArticleType(article)));
        } catch (IOException e){
            log.error(e.getMessage());
        }
        return articlesList;
    }

    public ArticleType filterByDesc(String descricpion) {
        List<ArticleType> articleTypes = getAll();
        return articleTypes.stream()
                .filter(linea -> linea.getDescription().equals(descricpion))
                .findFirst().orElse(null);
    }

}

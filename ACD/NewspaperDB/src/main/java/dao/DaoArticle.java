package dao;

import config.ConfigProperties;
import config.ConfigYaml;
import domain.modelo.Article;
import domain.modelo.ArticleType;
import domain.modelo.Newspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DaoArticle {

    // getAll(either)
    // get (Article)
    // save/add (int)
    // update (int)
    // delete (int)

    public List<Article> getAll() {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathArticles"));
        List<Article> articlesList = new ArrayList<>();

        try {
            List<String> articles = Files.readAllLines(file);
            articles.forEach(article ->
                    articlesList.add(new Article(article)));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return articlesList;
    }



    public void save(Article a) {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathArticles"));
        try {
            Files.write(file, a.toStringTextFile().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public Either<String, Boolean> delete(int id) {
        Either<String, Boolean> respuesta;
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathArticles"));
        List<Article> articles = getAll();
        try {
            List<String> articlesData = Files.readAllLines(file);
            Article art = articles.stream().filter(article ->
                            article.getArticleID() == id)
                    .findFirst().orElse(null);
            if (art!=null){
                articles.remove(art);
                articlesData.remove(art.toStringTextFile());
                Files.write(file, articlesData);
                respuesta = Either.right(true);
            } else {
                respuesta = Either.left("Error, there are not any articles with that ID");
            }


        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }


}

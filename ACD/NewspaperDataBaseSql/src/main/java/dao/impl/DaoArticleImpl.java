package dao.impl;

import config.ConfigProperties;
import dao.DaoArticle;
import dao.strings.DaoConstants;
import model.Article;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DaoArticleImpl implements DaoArticle {

    @Override public List<Article> getAll() {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty(DaoConstants.PATH_ARTICLES));
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



    @Override public void save(Article a) {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty(DaoConstants.PATH_ARTICLES));
        try {
            Files.write(file, a.toStringTextFile().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override public Either<String, Boolean> delete(int id) {
        Either<String, Boolean> respuesta;
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty(DaoConstants.PATH_ARTICLES));
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
                respuesta = Either.left(DaoConstants.ERROR_THERE_ARE_NOT_ANY_ARTICLES_WITH_THAT_ID);
            }


        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }


}

package dao;

import config.ConfigProperties;
import config.ConfigYaml;
import domain.modelo.Article;
import domain.modelo.ArticleType;
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

    DaoType daoType;

    // getAll(either)
    // get (Article)
    // save/add (int)
    // update (int)
    // delete (int)

    @Inject
    public DaoArticle(DaoType daoType) {
        this.daoType = daoType;
    }

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

    public List<Article> getArticlesFilter(String description) {
        List<Article> articlesList = getAll();
        ArticleType articleTypes = daoType.getFilter(description);
        if (articleTypes == null) {
            return Collections.emptyList();
        } else {
            return articlesList.stream()
                    .filter(article ->
                            article.getTypeID() == articleTypes.getTypeID())
                    .collect(Collectors.toList());
        }
    }

    public boolean save(Article a) {
        Path file = Paths.get(ConfigProperties.getInstance()
                .getProperty("pathArticles"));
        try {
            List<String> articles = Files.readAllLines(file);
            articles.add(a.toStringTextFile());
            Files.write(file, articles);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }


}

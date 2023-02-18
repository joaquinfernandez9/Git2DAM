package spring.controllers;

import domain.model.Article;
import domain.usecases.ArticlesServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {


    private final ArticlesServices articlesService;

    public ArticlesController(ArticlesServices articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articlesService.getAll();
    }

    @GetMapping("/newspaper/{name}")
    public List<Article> getArticlesByNewspaperName(@PathVariable String name) {
        return articlesService.getArticlesByNewspaperName(name);
    }

    @GetMapping("/newspaper/{id}")
    public List<Article> getArticlesByNewspaperId(@PathVariable Integer id) {
        return articlesService.getArticlesByNewspaperId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article saveArticle(@RequestBody Article article) {
        return articlesService.save(article);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Integer id) {
        articlesService.deleteById(id);
    }


}

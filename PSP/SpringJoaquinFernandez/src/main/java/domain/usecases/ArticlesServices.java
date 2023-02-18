package domain.usecases;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import data.ArticleEntityRepository;
import data.modelo.mapper.ArticleMapper;
import domain.model.Article;


import java.util.List;


@Service
@Log4j2
public class ArticlesServices {

    private final ArticleEntityRepository articlesRepository;

    private final ArticleMapper articleMapper;

    public ArticlesServices(ArticleEntityRepository articlesRepository, ArticleMapper articleMapper) {
        this.articlesRepository = articlesRepository;
        this.articleMapper = articleMapper;
    }

    public List<Article> getAll(){
        return articlesRepository.findAll()
                .stream()
                .map(articleMapper::toArticle)
                .toList();
    }

    public List<Article> getArticlesByNewspaperName(String name){
        return articlesRepository.findByNewspaperName(name)
                .stream()
                .map(articleMapper::toArticle)
                .toList();
    }

    public List<Article> getArticlesByNewspaperId(Integer id){
        return articlesRepository.findByNewspaperId(id)
                .stream()
                .map(articleMapper::toArticle)
                .toList();
    }

    public Article save(Article article){
        return articleMapper.toArticle(articlesRepository.save(articleMapper.toArticleEntity(article)));
    }

    public void deleteById(Integer id){
        articlesRepository.deleteById(id);
    }





}

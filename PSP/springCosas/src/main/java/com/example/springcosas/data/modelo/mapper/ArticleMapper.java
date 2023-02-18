package com.example.springcosas.data.modelo.mapper;

import com.example.springcosas.data.modelo.ArticleEntity;
import com.example.springcosas.domain.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    private NewspaperMapper newspaperMapper;

    public ArticleMapper(NewspaperMapper newspaperMapper) {
        this.newspaperMapper = newspaperMapper;
    }

    public Article toArticle(ArticleEntity article) {
        return new Article(article.getId(),
                article.getName(),
                newspaperMapper.toNewspaper(article.getNewspaper()));
    }

    public ArticleEntity toArticleEntity(Article article) {
        return new ArticleEntity(article.id(),
                article.name(),
                newspaperMapper.toNewspaperEntity(article.newspaper()));
    }

}

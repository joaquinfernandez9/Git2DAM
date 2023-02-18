package data.modelo.mapper;

import data.modelo.ArticleEntity;
import domain.model.Article;
import domain.model.Newspaper;
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

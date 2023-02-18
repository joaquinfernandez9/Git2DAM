package data;


import data.modelo.ArticleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Integer> {

    @Query("SELECT a FROM ArticleEntity a")
    List<ArticleEntity> findAll();

    @Transactional
    @Query("SELECT a FROM ArticleEntity a WHERE a.newspaper.name = :name")
    List<ArticleEntity> findByNewspaperName(String name);

    @Transactional
    @Query("SELECT a FROM ArticleEntity a WHERE a.newspaper.id = :id")
    List<ArticleEntity> findByNewspaperId(Integer id);

    @Transactional
    @Override
    ArticleEntity save(ArticleEntity articleEntity);

    @Transactional
    @Override
    void deleteById(Integer integer);

//    ArticleEntity insert(ArticleEntity articleEntity);


}

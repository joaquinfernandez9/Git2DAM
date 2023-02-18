package com.example.springcosas.data;


import com.example.springcosas.data.modelo.ArticleEntity;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ArticleEntityRepository extends JpaRepository<ArticleEntity, Integer> {

    @Override
    List<ArticleEntity> findAll();

    @Transactional
    @Override
    ArticleEntity save(ArticleEntity articleEntity);

    @Transactional
    @Override
    void deleteById(Integer integer);

    //para que esto este bien deberian estar en el repositorio de newspaper
    @Transactional
    @Query("SELECT a FROM ArticleEntity a WHERE a.newspaper.id = :id")
    List<ArticleEntity> findByNewspaperId(Integer id);
    @Transactional
    @Query("SELECT a FROM ArticleEntity a WHERE a.newspaper.name = :name")
    List<ArticleEntity> findByNewspaperName(String name);


}

package com.example.springcosas.data;

import com.example.springcosas.data.modelo.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewspaperEntityRepository extends JpaRepository<NewspaperEntity, Integer> {

    @Override
    List<NewspaperEntity> findAll();

    @Query("SELECT n FROM NewspaperEntity n WHERE n.id = :id")
    NewspaperEntity getById(@Param("id") Integer id);

    @Query("SELECT n FROM NewspaperEntity n WHERE n.name = :name")
    NewspaperEntity getByName(@Param("name") String name);


    @Query("SELECT n FROM NewspaperEntity n WHERE n.name LIKE %:name%")
    List<NewspaperEntity> getByNameLike(@Param("name") String name);

    @Override
    NewspaperEntity save(NewspaperEntity newspaperEntity);

    @Override
    void deleteById(Integer integer);



}

package data;

import data.modelo.NewspaperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewspaperEntityRepository extends JpaRepository<NewspaperEntity, Integer> {

    @Query("SELECT n FROM NewspaperEntity n")
    List<NewspaperEntity> getAll();

    @Query("SELECT n FROM NewspaperEntity n WHERE n.id = :id")
    NewspaperEntity getById(@Param("id") Integer id);

    @Query("SELECT n FROM NewspaperEntity n WHERE n.name = :name")
    NewspaperEntity getByName(@Param("name") String name);

    @Query("SELECT n FROM NewspaperEntity n WHERE n.releaseDate = :releaseDate")
    NewspaperEntity getByReleaseDate(@Param("releaseDate") String releaseDate);

    @Query("SELECT n FROM NewspaperEntity n WHERE n.name LIKE %:name%")
    List<NewspaperEntity> getByNameLike(@Param("name") String name);




}

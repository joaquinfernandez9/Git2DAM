package com.example.springcosas.domain.usecases;

import com.example.springcosas.data.NewspaperEntityRepository;
import com.example.springcosas.data.modelo.mapper.NewspaperMapper;
import com.example.springcosas.domain.model.Newspaper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class NewspaperServices  {
    private final NewspaperEntityRepository repo;

    private final NewspaperMapper mapper;

    public NewspaperServices(NewspaperEntityRepository repo, NewspaperMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }

    public Newspaper save(Newspaper newspaper){
        return mapper.toNewspaper(repo.save(mapper.toNewspaperEntity(newspaper)));
    }

    public List<Newspaper> getAll(){
        return repo.findAll()
                .stream()
                .map(mapper::toNewspaper)
                .toList();
    }

    public Newspaper getById(Integer id){
        return mapper.toNewspaper(repo.getById(id));
    }

    public Newspaper getByName(String name){
        return mapper.toNewspaper(repo.getByName(name));
    }


    public List<Newspaper> getByNameLike(String name){
        return repo.getByNameLike(name)
                .stream()
                .map(mapper::toNewspaper)
                .toList();
    }


}

package com.example.springcosas.spring.controllers;

import com.example.springcosas.domain.model.Newspaper;
import com.example.springcosas.domain.usecases.NewspaperServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController {
    private NewspaperServices newspaperService;

    public NewspaperController(NewspaperServices newspaperService) {
        this.newspaperService = newspaperService;
    }

    @GetMapping
    public List<Newspaper> getAllNewspapers() {
        return newspaperService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Newspaper saveNewspaper(@RequestBody Newspaper newspaper) {
        return newspaperService.save(newspaper);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewspaper(@PathVariable Integer id) {
        newspaperService.deleteById(id);
    }

    // get by id
    @GetMapping("/id/{id}")
    public Newspaper getNewspaperById(@PathVariable Integer id) {
        return newspaperService.getById(id);
    }

    // get by name
    @GetMapping("/name/{name}")
    public Newspaper getNewspaperByName(@PathVariable String name) {
        return newspaperService.getByName(name);
    }
    //get by name like
    @GetMapping("/name/like/{name}")
    public List<Newspaper> getNewspaperByNameLike(@PathVariable String name) {
        return newspaperService.getByNameLike(name);
    }


}

package spring.controllers;

import domain.usecases.NewspaperServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController {
    private NewspaperServices newspaperService;

    public NewspaperController(NewspaperServices newspaperService) {
        this.newspaperService = newspaperService;
    }


}

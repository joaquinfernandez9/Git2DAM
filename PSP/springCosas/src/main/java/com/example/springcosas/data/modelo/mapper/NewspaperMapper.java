package com.example.springcosas.data.modelo.mapper;

import com.example.springcosas.data.modelo.NewspaperEntity;
import com.example.springcosas.domain.model.Newspaper;
import org.springframework.stereotype.Component;

@Component
public class NewspaperMapper {

        public NewspaperMapper() {
        }

        public Newspaper toNewspaper(NewspaperEntity newspaper) {
            return new Newspaper(newspaper.getId(),
                    newspaper.getName(), newspaper.getReleaseDate());
        }

        public NewspaperEntity toNewspaperEntity(Newspaper newspaper) {
            return new NewspaperEntity(newspaper.id(),
                    newspaper.name(), newspaper.relDate());
        }
}

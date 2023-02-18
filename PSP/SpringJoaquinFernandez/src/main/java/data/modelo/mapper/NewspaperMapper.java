package data.modelo.mapper;

import domain.model.Newspaper;
import org.springframework.stereotype.Component;

@Component
public class NewspaperMapper {

        public NewspaperMapper() {
        }

        public Newspaper toNewspaper(data.modelo.NewspaperEntity newspaper) {
            return new Newspaper(newspaper.getId(),
                    newspaper.getName(), newspaper.getReleaseDate());
        }

        public data.modelo.NewspaperEntity toNewspaperEntity(Newspaper newspaper) {
            return new data.modelo.NewspaperEntity(newspaper.id(),
                    newspaper.name(), newspaper.relDate());
        }
}

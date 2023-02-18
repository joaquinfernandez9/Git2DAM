package services;

import model.Newspaper;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface NewspaperServ {
    List<Newspaper> getAll();

    int deleteNewspaper(ObjectId id);

    int add(Newspaper n);

    int update(Newspaper n);

    Map<String, Integer> getNbrArticles(ObjectId newspaper);
}

package dao;

import model.Newspaper;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface DaoNewspaper {
    List<Newspaper> getAll();

    Newspaper get(ObjectId id);

    int add(Newspaper n);

    Map<String, Integer> getNbrArticles(ObjectId newspaper);

    int delete(ObjectId id);

    int update(Newspaper n);
}

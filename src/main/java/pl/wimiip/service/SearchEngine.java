package pl.wimiip.service;

import pl.wimiip.model.Document;
import pl.wimiip.model.Type;

import java.util.List;

/**
 * Created by nishi on 2016-06-07.
 */
public interface SearchEngine {
    public List<Document> findByType(Type type);
    public List<Document> listAll();
}

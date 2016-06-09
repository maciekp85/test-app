package pl.wimiip.unitTests.service;

import org.junit.Test;
import pl.wimiip.model.Document;
import pl.wimiip.model.Type;
import pl.wimiip.service.SearchEngine;
import pl.wimiip.service.impl.SearchEngineService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nishi on 2016-06-07.
 */
public class SearchEngineServiceTest {

    private SearchEngine engine = new SearchEngineService();

    @Test
    public void testFindByType() {
        Type documentType = new Type();
        documentType.setName("WEB");
        documentType.setDesc("Lacze sieciowe");
        documentType.setExtension(".url");

        List<Document> documents = engine.findByType(documentType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(documentType.getName(), documents.get(0).getType().getName());
        assertEquals(documentType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(documentType.getExtension(), documents.get(0).getType().getExtension());
    }

    @Test
    public void testListAll() {
        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }

}

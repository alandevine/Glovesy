package Database;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.rmi.AccessException;
import java.util.List;
import java.util.NoSuchElementException;

interface DBHandler {

    MongoCollection<Document> collection = null;

    void addEntry(Document doc);

    List<Document> findAllEntries();
    Document findEntry(Document query) throws NoSuchElementException, AccessException;
    Boolean containsEntry(Document query);
    void deleteEntry(String query);
}

package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.*;

public class GloveConfigurationHandler implements DBHandler {

    private final MongoCollection<Document> collection;

    public GloveConfigurationHandler(String url, String collectionName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(collectionName);
        collection = database.getCollection("configuration");
    }

    public void addAll(GloveConfiguration gloveConfiguration) {
        for (Map.Entry<String, Double> entry : gloveConfiguration.toHashMap().entrySet())
            collection.insertOne(new Document("field", entry.getKey()).append("value", entry.getValue()));
    }

    @Override
    public void addEntry(Document doc) {
        collection.insertOne(doc);
    }

    @Override
    public List<Document> findAllEntries() {
        List<Document> fields = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document doc : iterable) fields.add(doc);
        return fields;
    }

    @Override
    public Document findEntry(String query) throws NoSuchElementException {
        FindIterable<Document> docs = collection.find(Filters.eq("field", query));
        Iterator<Document> doc = docs.iterator();
        Document entry = doc.next();
        System.out.println(entry);
        return entry;
    }

    @Override
    public Boolean containsEntry(Document query) {
        return null;
    }

    @Override
    public void deleteEntry(String query) {

    }
}

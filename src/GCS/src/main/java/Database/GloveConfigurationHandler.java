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

        List<Document> fields = findAllEntries();
        if (fields.size() == 0) {
            this.populateDefaults();
            this.addEntry(new Document("_id", "defaultConfig").append("value", true));
        }
    }

    private void populateDefaults() {
        GloveConfiguration defaults = new GloveConfiguration();
        this.addAll(defaults);
    }

    public void addAll(GloveConfiguration gloveConfiguration) {
        for (Map.Entry<String, Double> entry : gloveConfiguration.toHashMap().entrySet())
            collection.insertOne(new Document("_id", entry.getKey()).append("value", entry.getValue()));
    }

    public void updateValue(String key, double value) {
        collection.replaceOne(Filters.eq("_id", key), new Document("value", value));
    }

    public void updateValue(String key, boolean value) {
        collection.replaceOne(Filters.eq("_id", key), new Document("value", value));
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
        FindIterable<Document> docs = collection.find(Filters.eq("_id", query));
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
        collection.deleteOne(Filters.eq("field", query));
    }

    private void deleteAll() {
        List<Document> apps = this.findAllEntries();
        for (Document app : apps) {
            this.deleteEntry(app.getString("field"));
        }
    }
}

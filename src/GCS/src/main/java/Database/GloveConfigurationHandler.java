package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    public Document findEntry(Document query) throws NoSuchElementException, AccessException {
        return null;
    }

    @Override
    public Boolean containsEntry(Document query) {
        return null;
    }

    @Override
    public void deleteEntry(String query) {

    }

    public static void main(String[] args) {
        GloveConfiguration gc = new GloveConfiguration();
        GloveConfigurationHandler gch = new GloveConfigurationHandler( "mongodb://127.0.0.1:27017", "glovesy");
//        gch.addAll(gc);

        for (Document entry : gch.findAllEntries()) {
            System.out.println(entry);

        }
    }
}

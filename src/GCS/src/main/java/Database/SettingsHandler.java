package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SettingsHandler {
    private final MongoCollection<Document> collection;

    public SettingsHandler(String url, String dbName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(dbName);
        collection = database.getCollection("gloveConfiguration");
    }

    public void addEntry(Document settings) {
        collection.insertOne(settings);
    }

    public List<Document> findAllEntries() {
        List<Document> settings = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document doc : iterable) settings.add(doc);
        return settings;
    }

}

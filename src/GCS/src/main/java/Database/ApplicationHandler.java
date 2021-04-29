package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Interface between the caller program and the MongoDB instance
 */
public class ApplicationHandler implements DBHandler {
    private final MongoCollection<Document> collection;

    /**
     * Constructor
     * @param url Url to MongoDB instance
     * @param collectionName Name of collection of documents
     */
    public ApplicationHandler(String url, String collectionName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(collectionName);
        collection = database.getCollection("applications");
    }

    public void addEntry(Document query) {
        System.out.println(query.toString());
        if (!isValidFile(query))
            throw new IllegalArgumentException("");

        collection.insertOne(query);
    }

    private boolean isValidFile(Document query) {
        return !this.containsEntry(query) && Application.verifyExecutable(query.getString("path"));
    }

    public List<Document> findAllEntries() {
        List<Document> apps = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) apps.add(document);
        return apps;
    }

    public Document findEntry(String query) throws NoSuchElementException, AccessException {
        FindIterable<Document> docs = collection.find(Filters.eq("name", query));
        Iterator<Document> doc = docs.iterator();
        return doc.next();
    }

    public Boolean containsEntry(Document query) {
        try {
            this.findEntry(query.getString("name"));
            return true;
        } catch (NoSuchElementException | AccessException ex) {
            return false;
        }
    }

    /**
     * Method for deleting a single entry.
     * @param name Name of program entry to be deleted
     */
    public void deleteEntry(String name) {
        collection.deleteOne(Filters.eq("name", name));
    }

    /**
     * Method for deleting all entries. Should only be used in testing scenarios.
     */
    private void deleteAll() {
        List<Document> apps = this.findAllEntries();
        for (Document app : apps) {
            this.deleteEntry(app.getString("name"));
        }
    }
}

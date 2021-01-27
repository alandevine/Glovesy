package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Interface between the caller program and the MongoDB instance
 */
public class ApplicationHandler {
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

    /**
     * Method for adding a single entry
     * @param name Name of program
     * @param path Path to program's executable
     */
    public void addEntry(String name, String path) throws FileNotFoundException, AccessException {
        if (this.containsEntry(name))
            throw new MongoException(String.format("The Entry \"%s\" is already contained in the attached database", name));

        if (!Application.verifyExecutable(path))
            throw new AccessException(String.format("The file \"%s\" is not an executable.", path));

        collection.insertOne(new Document("name", name).append("path", path));
    }

    /**
     * Method for returning all program entries
     * @return List of Application objects
     */
    public List<Application> findAllEntries() throws FileNotFoundException, AccessException {
        List<Application> apps = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) apps.add(new Application(document));
        return apps;
    }

    /**
     * Method for finding a single Document
     * @param name Name of program
     * @return The desired Application object
     * @throws NoSuchElementException In the event that there is no such application, a NoSuchElementException will be thrown.
     */
    public Application findEntry(String name) throws NoSuchElementException, FileNotFoundException, AccessException {
        FindIterable<Document> docs = collection.find(Filters.eq("name", name));
        Iterator<Document> doc = docs.iterator();
        return new Application(doc.next());
    }

    /**
     *
     * @param name Name of application
     * @return Boolean dependent on the provided name being found in the document collection
     */
    public Boolean containsEntry(String name) {
        try {
            this.findEntry(name);
            return true;
        } catch (NoSuchElementException | FileNotFoundException | AccessException ex) {
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
    public void deleteAll() throws FileNotFoundException, AccessException {
        List<Application> apps = this.findAllEntries();
        for (Application app : apps) {
            this.deleteEntry(app.getName());
        }
    }

    public static void main(String[] args) throws FileNotFoundException, AccessException {
        ApplicationHandler db = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
        db.deleteEntry("bad");
    }
}

package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationHandler {
    private final MongoCollection<Document> collection;

    public ApplicationHandler(String url, String collectionName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(collectionName);
        collection = database.getCollection("applications");
    }

    public void addEntry(String name, String path) {
        if (!this.containsEntry(name))
            collection.insertOne(new Document("name", name).append("path", path));
        else
            throw new MongoException(String.format("The Entry \"%s\" is already contained in the attached database", name));
    }

    public List<Application> findAllEntrys() {
        List<Application> apps = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        Iterator<Document> iterator = iterable.iterator();
        Application curr;
        while (iterator.hasNext()) {
            curr = new Application(iterator.next());
            apps.add(curr);
        }
        return apps;
    }

    public Application findEntry(String name) throws NoSuchElementException {
        FindIterable<Document> docs = collection.find(Filters.eq("name", name));
        Iterator<Document> doc = docs.iterator();
        return new Application(doc.next());
    }

    public Boolean containsEntry(String name) {
        try {
            this.findEntry(name);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void deleteEntry(String name) {
        collection.deleteOne(Filters.eq("name", name));
    }

    public void deleteAll() {
        List<Application> apps = this.findAllEntrys();
        for (Application app : apps) {
            this.deleteEntry(app.getName());
        }
    }

    public static void main(String[] args) {
        ApplicationHandler db = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
        db.addEntry("test", "foo/bar");
        System.out.println(db.findEntry("test"));
    }
}

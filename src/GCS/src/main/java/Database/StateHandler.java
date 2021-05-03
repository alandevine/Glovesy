package Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.rmi.AccessException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.mongodb.client.model.Aggregates.group;

public class StateHandler implements DBHandler {

    private final MongoCollection<Document> collection;

    public StateHandler(String url, String collectionName) {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase database = mongoClient.getDatabase(collectionName);
        collection = database.getCollection("state");
    }

    public void addState(ConcurrentHashMap<String, Double> state) {

        for (Map.Entry<String, Double> entry : state.entrySet())
            this.addEntry(new Document("field", entry.getKey())
                               .append("value", entry.getValue()));
    }

    public Document findMax() {
        Document maxValues = new Document();

        AggregateIterable<Document> aggregate = collection.aggregate(
                Arrays.asList(
                        group("$field", Accumulators.max("value", "$value"))
                )
        );

        for (Document doc : aggregate) {
            maxValues.append(doc.getString("_id"), doc.getDouble("value"));
        }

        return maxValues;
    }

    public Document findMin() {
        Document maxValues = new Document();

        AggregateIterable<Document> aggregate = collection.aggregate(
                Arrays.asList(
                        group("$field", Accumulators.min("value", "$value"))
                )
        );

        for (Document doc : aggregate) {
            maxValues.append(doc.getString("_id"), doc.getDouble("value"));
        }

        return maxValues;
    }

    @Override
    public void addEntry(Document doc) {
        collection.insertOne(doc);
    }

    @Override
    public List<Document> findAllEntries() {
        List<Document> state = new ArrayList<>();
        FindIterable<Document> iterable = collection.find();
        for (Document document : iterable) state.add(document);
        return state;
    }

    @Override
    public Document findEntry(String query) throws NoSuchElementException, AccessException {
        FindIterable<Document> docs = collection.find(Filters.eq("field", query));
        Iterator<Document> doc = docs.iterator();
        return doc.next();
    }

    @Override
    public Boolean containsEntry(Document query) {
        return null;
    }

    @Override
    public void deleteEntry(String query) {

    }
}

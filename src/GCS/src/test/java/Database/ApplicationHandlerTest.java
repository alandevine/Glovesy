package Database;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationHandlerTest {

    ApplicationHandler db = new ApplicationHandler("mongodb://127.0.0.1:27017", "test");

    @Test
    void addEntry() {
        db.deleteAll();
        db.addEntry(new Document("name", "gcc").append("path", "/usr/bin/gcc"));
        assertTrue(db.containsEntry(new Document("name", "gcc")));
        db.deleteAll();
    }

    @Test
    void findAllEntries() throws FileNotFoundException, AccessException {
        db.deleteAll();
        db.addEntry(new Document("name", "gcc").append("path", "/usr/bin/gcc"));
        db.addEntry(new Document("name", "g++").append("path", "/usr/bin/g++"));
        List<Document> apps = db.findAllEntries();
        assertEquals(apps.size(), 2);
        assertEquals(new Application(apps.get(0)).getName(), "gcc");
        assertEquals(new Application(apps.get(0)).getPath(), "/usr/bin/gcc");
        assertEquals(new Application(apps.get(1)).getName(), "g++");
        assertEquals(new Application(apps.get(1)).getPath(), "/usr/bin/g++");
        db.deleteAll();
    }

    @Test
    void findEntry() throws AccessException, FileNotFoundException {
        db.deleteAll();
        db.addEntry(new Document("name", "gcc").append("path", "/usr/bin/gcc"));
        String actual = "Application:\n"
                        + "\tname: gcc\n"
                        + "\tpath: /usr/bin/gcc\n";

        assertEquals(new Application(db.findEntry("gcc")).toString(), actual);
        db.deleteAll();
    }

    @Test
    void containsEntry() {
        db.deleteAll();
        db.addEntry(new Document("name", "gcc").append("path", "/usr/bin/gcc"));
        assertTrue(db.containsEntry(new Document("name", "gcc")));
        db.deleteAll();
    }

    @Test
    void deleteEntry() {
        db.deleteAll();
        db.addEntry(new Document("name", "gcc").append("path", "/usr/bin/gcc"));
        assertTrue(db.containsEntry(new Document("name", "gcc")));
        db.deleteEntry("gcc");
        assertFalse(db.containsEntry(new Document("name", "gcc")));
    }
}
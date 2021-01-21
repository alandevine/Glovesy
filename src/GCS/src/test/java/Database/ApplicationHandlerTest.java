package Database;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationHandlerTest {

    ApplicationHandler db = new ApplicationHandler("mongodb://127.0.0.1:27017", "test");

    @Test
    void addEntry() {
        db.addEntry("test", "/foo/bar");
        assertTrue(db.containsEntry("test"));
        db.deleteAll();
    }

    @Test
    void findAllEntries() {
        db.addEntry("test1", "/foo/bar");
        db.addEntry("test2", "/foo/bar");
        List<Application> apps = db.findAllEntries();
        assertEquals(apps.size(), 2);
        assertEquals(apps.get(0).getName(), "test1");
        assertEquals(apps.get(0).getPath(), "/foo/bar");
        assertEquals(apps.get(1).getName(), "test2");
        assertEquals(apps.get(1).getPath(), "/foo/bar");
    }

    @Test
    void findEntry() {
        db.addEntry("test", "/foo/bar");
        String expected = "Application:\n"
                        + "\tname: test\n"
                        + "\tpath: /foo/bar\n";

        assertEquals(db.findEntry("test").toString(), expected);
        db.deleteAll();
    }

    @Test
    void containsEntry() {
        db.addEntry("test", "/foo/bar");
        assertTrue(db.containsEntry("test"));
        db.deleteAll();
    }

    @Test
    void deleteEntry() {
        db.deleteEntry("test");
        assertFalse(db.containsEntry("test"));
    }
}
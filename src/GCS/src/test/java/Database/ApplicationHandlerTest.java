package Database;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationHandlerTest {
//
//    ApplicationHandler db = new ApplicationHandler("mongodb://127.0.0.1:27017", "test");
//
//    @Test
//    void addEntry() throws FileNotFoundException, AccessException {
//        db.deleteAll();
//        db.addEntry("gcc", "/usr/bin/gcc");
//        assertTrue(db.containsEntry("gcc"));
//        db.deleteAll();
//    }
//
//    @Test
//    void findAllEntries() throws FileNotFoundException, AccessException {
//        db.deleteAll();
//        db.addEntry("gcc", "/usr/bin/gcc");
//        db.addEntry("g++", "/usr/bin/g++");
//        List<Application> apps = db.findAllEntries();
//        assertEquals(apps.size(), 2);
//        assertEquals(apps.get(0).getName(), "gcc");
//        assertEquals(apps.get(0).getPath(), "/usr/bin/gcc");
//        assertEquals(apps.get(1).getName(), "g++");
//        assertEquals(apps.get(1).getPath(), "/usr/bin/g++");
//        db.deleteAll();
//    }
//
//    @Test
//    void findEntry() throws FileNotFoundException, AccessException {
//        db.deleteAll();
//        db.addEntry("gcc", "/usr/bin/gcc");
//        String actual = "Application:\n"
//                        + "\tname: gcc\n"
//                        + "\tpath: /usr/bin/gcc\n";
//
//        assertEquals(db.findEntry("gcc").toString(), actual);
//        db.deleteAll();
//    }
//
//    @Test
//    void containsEntry() throws FileNotFoundException, AccessException {
//        db.deleteAll();
//        db.addEntry("gcc", "/usr/bin/gcc");
//        assertTrue(db.containsEntry("gcc"));
//        db.deleteAll();
//    }
//
//    @Test
//    void deleteEntry() throws FileNotFoundException, AccessException {
//        db.deleteAll();
//        db.addEntry("gcc", "/usr/bin/gcc");
//        assertTrue(db.containsEntry("gcc"));
//        db.deleteEntry("gcc");
//        assertFalse(db.containsEntry("gcc"));
//    }
}
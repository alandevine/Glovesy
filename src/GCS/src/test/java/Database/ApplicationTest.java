package Database;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    Application app;

    ApplicationTest() throws FileNotFoundException {
        app = new Application("gcc", "/usr/bin/gcc");
    }

    @Test
    void getName() {
        assertEquals(app.getName(), "gcc");
    }

    @Test
    void getPath() {
        assertEquals(app.getPath(), "/usr/bin/gcc");
    }

    @Test
    void testToString() {
        String actual = "Application:\n"
                      + "\tname: gcc\n"
                      + "\tpath: /usr/bin/gcc\n";
        assertEquals(app.toString(), actual);
    }

    @Test
    void setName() {
        app.setName("g++");
        assertEquals(app.getName(), "g++");
    }

    @Test
    void setPath() throws FileNotFoundException {
        app.setPath("/usr/bin/g++");
        assertEquals(app.getPath(), "/usr/bin/g++");
    }

    @Test
    void setPathException() {
        Exception e = assertThrows(FileNotFoundException.class, () -> app.setPath("/usr/bin/aprogramthatdoesntexist"));

        assertEquals(e.getMessage(), String.format("The file \"%s\" does not exist", "/usr/bin/aprogramthatdoesntexist"));
    }
}
package Database;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {
    Application app;
    File testFile;

    ApplicationTest() throws IOException {
        app = new Application("gcc", "/usr/bin/gcc");
        String cwd = new File("").getAbsolutePath();
        testFile = new File(cwd + "/foo");
        testFile.createNewFile();
        testFile.deleteOnExit();
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
    void setPathSuccess() throws FileNotFoundException, AccessException {
        app.setPath("/bin/g++");
        assertEquals(app.getPath(), "/bin/g++");
    }

    @Test
    void setPathBadPathException() {
        Exception e = assertThrows(FileNotFoundException.class, () -> app.setPath("/usr/bin/aprogramthatdoesntexist"));
        assertEquals(e.getMessage(), String.format("The file \"%s\" does not exist", "/usr/bin/aprogramthatdoesntexist"));
    }

    @Test
    void setPathNonExecutableException() {
         assertThrows(AccessException.class, () -> app.setPath(testFile.getPath()));
    }
}
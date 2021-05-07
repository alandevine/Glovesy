package Database;

import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private String name;
    private String path;

    public Application(Document doc) throws FileNotFoundException, AccessException {
        this.name = (String) doc.get("name");
        setPath((String) doc.get("path"));
    }

    public Application(String name, String path) throws FileNotFoundException, AccessException {
        this.name = name;
        setPath(path);
    }

    public Application(String name) {
        this.name = name;
    }

    public static List<Application> fromDocList(List<Document> allEntries) throws FileNotFoundException, AccessException {
        List <Application> apps = new ArrayList<>();
        for (Document doc : allEntries) apps.add(new Application(doc.getString("name"), doc.getString("path")));
        return apps;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public static boolean verifyPath(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static boolean verifyExecutable(String path) {
        File file = new File(path);
        return file.canExecute();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) throws FileNotFoundException, AccessException {
        if (!verifyPath(path))
            throw new FileNotFoundException(String.format("The file \"%s\" does not exist", path));
        if (!verifyExecutable(path))
            throw new AccessException(String.format("The file \"%s\" is not an executable.", path));
        this.path = path;
    }

    @Override
    public String toString() {
        return "Application:\n"
              + String.format("\tname: %s\n", this.name)
              + String.format("\tpath: %s\n", this.path);
    }
}

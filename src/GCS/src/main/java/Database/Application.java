package Database;

import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;

public class Application {

    private String name;
    private String path;

    public Application(Document doc) {
        this.name = (String) doc.get("name");
        this.path = (String) doc.get("path");
    }

    public Application(String name, String path) throws FileNotFoundException {
        this.name = name;
        if (this.verifyPath(path))
            this.path = path;
        else
            throw new FileNotFoundException();
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    private boolean verifyPath(String path) {
        File file = new File(path);
        return file.exists();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) throws FileNotFoundException {
        if (this.verifyPath(path))
            this.path = path;
        else
            throw new FileNotFoundException(String.format("The file \"%s\" does not exist", path));
    }

    @Override
    public String toString() {
        return "Application:\n"
              + String.format("\tname: %s\n", this.name)
              + String.format("\tpath: %s\n", this.path);
    }
}

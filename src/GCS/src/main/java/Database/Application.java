package Database;

import org.bson.Document;

public class Application {

    private String name;
    private String path;

    public Application(Document doc) {
        this.name = (String) doc.get("name");
        this.path = (String) doc.get("path");
    }

    public Application(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    @Override
    public String toString() {
        return "Application:\n"
              + String.format("\tname: %s\n", this.name)
              + String.format("\tpath: %s\n", this.path);
    }
}

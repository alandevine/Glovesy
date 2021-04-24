package Database;

import org.bson.Document;

public class Settings {
    private String name;
    private String path;
    private double actuationThreshold;
    private GloveConfiguration config;

    public Settings(String name, String path, double actuationThreshold) {
        this.name = name;
        this.path = path;
        this.actuationThreshold = actuationThreshold;

        config = new GloveConfiguration();
    }

    public Document toBson() {
        Document doc = new Document();
        doc.append("config", config.toBson());
        return doc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Document getConfig() {
        return config.toBson();
    }

    public void setConfig(Document config) {
        this.config.fromBson(config);
    }
}

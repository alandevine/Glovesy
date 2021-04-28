package gui;

import javafx.scene.control.Label;
import org.bson.Document;

public class ConfigLabel extends Label {
    public ConfigLabel (Document doc) {
        this.setText(doc.getString("field") + "\t" + doc.get("value"));
    }
}

package gui;

import Database.ApplicationHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.rmi.AccessException;

public class ApplicationLabel extends Label {

    public ApplicationLabel (Document doc, Controller scene) {
        this.setText(doc.getString("name"));
        ContextMenu menu = new ContextMenu();
        MenuItem item = new MenuItem("Delete Item");

        menu.getItems().add(item);
        this.setContextMenu(menu);

        item.setOnAction(event -> {
            ApplicationHandler applicationHandler = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
            applicationHandler.deleteEntry(doc.getString("name"));
            try {
                scene.getApplicationList().getChildren().clear();
                scene.populateApplicationList();
            } catch (FileNotFoundException | AccessException e) {
                e.printStackTrace();
            }
        });
    }
}

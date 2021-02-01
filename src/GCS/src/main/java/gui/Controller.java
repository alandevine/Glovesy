package gui;

import Database.Application;
import Database.ApplicationHandler;
import com.mongodb.MongoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewer.Hand;

import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.AccessException;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public VBox applicationList;
    @FXML public Button addApplication;
    @FXML public Hand handGroup;
    @FXML public AnchorPane handPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handGroup = new Hand(handPane.getMaxWidth() / 4,
                             handPane.getMaxHeight() / 4);
        handPane.getChildren().add(handGroup);

        try {
            populateApplicationList();
        } catch (FileNotFoundException | AccessException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayAddApp() {
        newApplicationPrompt();
    }

    void populateApplicationList() throws FileNotFoundException, AccessException {
        ApplicationHandler applicationHandler = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
        List<Application> apps = applicationHandler.findAllEntries();

        ApplicationLabel entry;
        for (Application app : apps) {
            entry = new ApplicationLabel(app.getName(), this);
            System.out.println(entry.toString());
            this.applicationList.getChildren().add(entry);
        }
    }

    private void newApplicationPrompt() {

        VBox elements = new VBox();
        HBox name = new HBox();
        HBox path = new HBox();
        HBox buttons = new HBox();

        Text appNameLabel = new Text("App Name");
        TextField appNameField = new TextField();

        name.getChildren().addAll(appNameLabel, appNameField);

        Text appPathLabel = new Text("App Path");
        TextField appPathField = new TextField();

        path.getChildren().addAll(appPathLabel, appPathField);

        Button cancel = new Button("Cancel");
        Button ok = new Button("OK");


        buttons.getChildren().addAll(cancel, ok);
        elements.getChildren().addAll(name, path, buttons);

        Scene appPrompt = new Scene(elements, 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Add App");
        stage.setScene(appPrompt);
        stage.show();

        cancel.setOnAction(event -> stage.close());

        ok.setOnAction(event -> {
            String nameContent = appNameField.getText();
            String pathContent = appPathField.getText();
            ApplicationHandler applicationHandler = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
            try {
                applicationHandler.addEntry( nameContent, pathContent);
                this.applicationList.getChildren().clear();
                this.populateApplicationList();
                stage.close();
            } catch (MongoException e) {
                elements.getChildren().add(new Text(String.format("The Entry \"%s\" has already been added.", nameContent)));
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                elements.getChildren().add(new Text(String.format("The Entry \"%s\" is not a valid path.", pathContent)));
                e.printStackTrace();
            } catch (AccessException e) {
                elements.getChildren().add(new Text(String.format("The file \"%s\" is not an executable.", pathContent)));
                e.printStackTrace();
            }
        });
    }

    public VBox getApplicationList() {
        return this.applicationList;
    }

    public void keyHandler(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case K:
                this.handGroup.rotateOnXAxis(+10);
                break;
            case J:
                this.handGroup.rotateOnXAxis(-10);
                break;
            case H:
                this.handGroup.rotateOnYAxis(-10);
                break;
            case L:
                this.handGroup.rotateOnYAxis(10);
                break;
        }
    }
}

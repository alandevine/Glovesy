package gui;

import Database.ApplicationHandler;
import Database.GloveConfigurationHandler;
import com.mongodb.MongoException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
import viewer.Hand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public VBox applicationList;
    @FXML public AnchorPane configList;
    @FXML public Button addApplication;
    @FXML public Hand handGroup;
    @FXML public AnchorPane handPane;
    @FXML public Button configurationButton;

    private final ApplicationHandler applicationHandler = new ApplicationHandler("mongodb://127.0.0.1:27017", "glovesy");
    private final GloveConfigurationHandler gloveConfigurationHandler = new GloveConfigurationHandler( "mongodb://127.0.0.1:27017", "glovesy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handGroup = new Hand(handPane.getMaxWidth() / 4,
                             handPane.getMaxHeight() / 4);
        handPane.getChildren().add(handGroup);

        try {
            populateApplicationList();
            populateGloveConfig();
        } catch (FileNotFoundException | AccessException e) {
            e.printStackTrace();
        }

        if (gloveConfigurationHandler.findEntry("defaultConfig").getBoolean("value")) {
            try {
                this.reconfigureGlove();
                this.gloveConfigurationHandler.updateValue("defaultConfig", false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void displayAddApp() {
        newApplicationPrompt();
    }

    void populateApplicationList() throws FileNotFoundException, AccessException {
        List<Document> apps = applicationHandler.findAllEntries();

        System.out.println(apps);

        ApplicationLabel entry;
        for (Document app : apps) {
            entry = new ApplicationLabel(app, this);
            System.out.println(entry);
            this.applicationList.getChildren().add(entry);
        }
    }

    @FXML
    public void reconfigureGlove() throws IOException {
        URL url =  new File("src/main/resources/configurationWindow.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 500, 500));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    void populateGloveConfig() throws AccessException {

        HBox columns = new HBox();
        VBox rowTitle = new VBox();
        rowTitle.setPadding(new Insets(10));
        rowTitle.getChildren().add(new Label(""));
        rowTitle.getChildren().add(new Label("Max"));
        rowTitle.getChildren().add(new Label("Min"));

        VBox thumb = new VBox();
        thumb.setPadding(new Insets(10));
        thumb.getChildren().add(new Label("Thumb"));
        thumb.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxThumbResistance")));
        thumb.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minThumbResistance")));

        VBox indexKnuckle = new VBox();
        indexKnuckle.setPadding(new Insets(10));
        indexKnuckle.getChildren().add(new Label("Index Knuckle"));
        indexKnuckle.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxIndexKnuckleResistance")));
        indexKnuckle.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minIndexKnuckleResistance")));

        VBox indexNeutro = new VBox();
        indexNeutro.setPadding(new Insets(10));
        indexNeutro.getChildren().add(new Label("Index Neutro"));
        indexNeutro.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxIndexNeutroResistance")));
        indexNeutro.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minIndexNeutroResistance")));

        VBox middleKnucle = new VBox();
        middleKnucle.setPadding(new Insets(10));
        middleKnucle.getChildren().add(new Label("Middle Knuckle "));
        middleKnucle.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxMiddleKnuckleResistance")));
        middleKnucle.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minMiddleKnuckleResistance")));

        VBox middleNeutro = new VBox();
        middleNeutro.setPadding(new Insets(10));
        middleNeutro.getChildren().add(new Label("Middle Neutro"));
        middleNeutro.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxMiddleNeutroResistance")));
        middleNeutro.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minMiddleNeutroResistance")));

        VBox ring = new VBox();
        ring.setPadding(new Insets(10));
        ring.getChildren().add(new Label("Ring"));
        ring.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxRingNeutroResistance")));
        ring.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minRingNeutroResistance")));

        VBox pinky = new VBox();
        pinky.setPadding(new Insets(10));
        pinky.getChildren().add(new Label("Pinky"));
        pinky.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("maxPinkyResistance")));
        pinky.getChildren().add(new ConfigLabel(gloveConfigurationHandler.findEntry("minPinkyResistance")));

        columns.getChildren().addAll(rowTitle, thumb, indexKnuckle, indexNeutro, middleKnucle, middleNeutro, ring, pinky);
        configList.getChildren().add(columns);


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
            try {
                applicationHandler.addEntry(new Document("name", nameContent).append("path", pathContent));
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
            case A:
                handGroup.contractThumb(5);
                break;
            case Q:
                handGroup.contractThumb(-5);
                break;
            case S:
                handGroup.contractIndex(5);
                break;
            case W:
                handGroup.contractIndex(-5);
                break;
            case D:
                handGroup.contractMiddle(5);
                break;
            case E:
                handGroup.contractMiddle(-5);
                break;
            case F:
                handGroup.contractRing(5);
                break;
            case R:
                handGroup.contractRing(-5);
                break;
            case G:
                handGroup.contractPinky(5);
                break;
            case T:
                handGroup.contractPinky(-5);
                break;
        }
    }
}

package viewer;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;


/**
 * Util class for common procedures
 * @TODO: 11/01/2021: Make this class generic
 */
public class util {

    /**
     * Method for adding material mask to a supplied node.
     * @param sphere Object to apply PhongMaterial.
     * @param red Double of red value.
     * @param green Double of green value.
     * @param blue Double of blue value.
     */
    public static void setColor(Sphere sphere, double red, double green, double blue) {
        PhongMaterial material = new PhongMaterial();
        Color color = new Color(red, green, blue, 1.0);
        material.setDiffuseColor(color);
        sphere.setMaterial(material);
    }
    public static void setColor(Cylinder cylinder, double red, double green, double blue) {
        PhongMaterial material = new PhongMaterial();
        Color color = new Color(red, green, blue, 1.0);
        material.setDiffuseColor(color);
        cylinder.setMaterial(material);
    }


    public static Line addEdge(Node a, Node b) {
        Line line = new Line();
        line.startXProperty().bind(a.layoutXProperty());
        line.startYProperty().bind(a.layoutYProperty());
        line.endXProperty().bind(b.layoutXProperty());
        line.endYProperty().bind(b.layoutYProperty());

        return line;
    }
}

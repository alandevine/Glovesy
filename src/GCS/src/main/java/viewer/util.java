package viewer;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

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

    public static Point3D sphereToPoint(Sphere sphere) {
        return new Point3D(sphere.getTranslateX(),
                           sphere.getTranslateY(),
                           sphere.getTranslateZ());
    }
}

package viewer;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

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

    public static void updatePhalange(Node parent, Node child, Node phalange) {
        Point3D a = new Point3D(parent.getTranslateX(), parent.getTranslateY(), parent.getTranslateZ());
        Point3D b = new Point3D(child.getTranslateX(), child.getTranslateY(), child.getTranslateZ());

        double distance = Math.abs(a.distance(b));
        Point3D midpoint = a.midpoint(b);

        Rotate rotateAboutX = new Rotate(VectorMath.getAngleYZ(a, b), Rotate.X_AXIS);
        Rotate rotateAboutZ = new Rotate(VectorMath.getAngleXY(a, b), Rotate.Z_AXIS);
        Transform finalRotation = rotateAboutX.createConcatenation(rotateAboutZ);

        Cylinder cylinder = (Cylinder) phalange;

        cylinder.setTranslateX(midpoint.getX());
        cylinder.setTranslateY(midpoint.getY());
        cylinder.setTranslateZ(midpoint.getZ());

        cylinder.setHeight(distance);
        cylinder.setRadius(5);

        cylinder.getTransforms().clear();
        cylinder.getTransforms().add(finalRotation);
    }

    public static Sphere addSphere(double yOffset, double[] color, double startX, double startY, double startZ) {
        Sphere sphere = new Sphere();

        sphere.setTranslateX(startX);
        sphere.setTranslateY(startY - yOffset);
        sphere.setTranslateZ(startZ);
        sphere.setRadius(10);

        util.setColor(sphere, color[0], color[1], color[2]);

        return sphere;
    }

    public static Point3D sphereToPoint(Sphere sphere) {
        return new Point3D(sphere.getTranslateX(),
                           sphere.getTranslateY(),
                           sphere.getTranslateZ());
    }
}

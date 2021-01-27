package viewer;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Hand extends Group {
    private Rotate r;
    private Transform t = new Rotate();
    private final int startX;
    private final int startY;

    private final Sphere[] homo = new Sphere[5];
    private final Sphere[] neutro = new Sphere[5];
    private final Sphere[] hetro = new Sphere[5];
    private final Sphere[] tip = new Sphere[5];
    private final Group phalanges = new Group();

    public Hand(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;

        for (int i = 0; i < homo.length; i++) {
            homo[i] = new Sphere();
            homo[i].setTranslateX(startX + 50 * i);
            homo[i].setTranslateY(startY / 2.0);
            homo[i].setTranslateZ(0);
            homo[i].setRadius(10);
            util.setColor(homo[i], 1, 0, 0);

            neutro[i] = new Sphere();
            neutro[i].setTranslateX(startX + 50 * i);
            neutro[i].setTranslateY((startY / 2.0) - 40);
            neutro[i].setTranslateZ(0);
            neutro[i].setRadius(10);
            util.setColor(neutro[i], 0, 1, 0);

            hetro[i] = new Sphere();
            hetro[i].setTranslateX(startX + 50 * i);
            hetro[i].setTranslateY((startY / 2.0) - 80);
            hetro[i].setTranslateZ(0);
            hetro[i].setRadius(10);
            util.setColor(hetro[i], 0, 0, 1);


            tip[i] = new Sphere();
            tip[i].setTranslateX(startX + 50 * i);
            tip[i].setTranslateY((startY / 2.0) - 120);
            tip[i].setTranslateZ(50);
            tip[i].setRadius(10);
            util.setColor(tip[i], 1, 0, 1);

            this.getChildren().addAll(homo[i], neutro[i], hetro[i], tip[i]);
        }

        this.connectAll();
        this.getChildren().add(phalanges);
    }

    public Sphere[] getIndex() {
        return new Sphere[] {homo[1], neutro[1], hetro[1], tip[1]};
    }

    public Sphere[] getMiddle() {
        return new Sphere[] {homo[2], neutro[2], hetro[2], tip[2]};
    }

    public Sphere[] getRing() {
        return new Sphere[] {homo[3], neutro[3], hetro[3], tip[3]};
    }

    public Sphere[] getPinky() {
        return new Sphere[] {homo[4], neutro[4], hetro[4], tip[4]};
    }

    public Sphere[] getThumb() {
        return new Sphere[] {homo[0], neutro[0], hetro[0], tip[0]};
    }

    private void connectAll() {
        this.phalanges.getChildren().clear();

        Cylinder[] thumbPhalanges = connectFinger(getThumb());

        for (Cylinder phalange : thumbPhalanges) {
            this.phalanges.getChildren().add(phalange);
        }

        Cylinder[] indexPhalanges = connectFinger(getIndex());

        for (Cylinder phalange : indexPhalanges) {
            this.phalanges.getChildren().add(phalange);
        }

        Cylinder[] middlePhalanges = connectFinger(getMiddle());

        for (Cylinder phalange : middlePhalanges) {
            this.phalanges.getChildren().add(phalange);
        }

        Cylinder[] ringPhalanges = connectFinger(getRing());

        for (Cylinder phalange : ringPhalanges) {
            this.phalanges.getChildren().add(phalange);
        }

        Cylinder[] pinkyPhalanges = connectFinger(getPinky());

        for (Cylinder phalange : pinkyPhalanges) {
            this.phalanges.getChildren().add(phalange);
        }
    }

    private Cylinder[] connectFinger(Sphere[] finger) {
        Cylinder[] fingerPhalanges = new Cylinder[3];
        Cylinder cylinder;
        Rotate rotate = new Rotate();
        rotate.setAxis(Rotate.X_AXIS);
        double distance;
        Point3D a;
        Point3D b;
        Point3D midpoint;

        for (int i = 0; i < finger.length - 1; i++) {
            cylinder = new Cylinder();
            a = new Point3D(finger[i].getTranslateX(), finger[i].getTranslateY(), finger[i].getTranslateZ());
            b = new Point3D(finger[i + 1].getTranslateX(), finger[i + 1].getTranslateY(), finger[i + 1].getTranslateZ());
            rotate.setAngle(-a.angle(b));

            distance = a.distance(b);
            midpoint = a.midpoint(b);

            cylinder.setTranslateX(midpoint.getX());
            cylinder.setTranslateY(midpoint.getY());
            cylinder.setTranslateZ(midpoint.getZ());

            cylinder.setHeight(distance);
            cylinder.setRadius(5);

            cylinder.getTransforms().add(rotate);
            util.setColor(cylinder, .2, .2, .2);

            fingerPhalanges[i] = cylinder;
            System.out.println(cylinder.getHeight());
        }

        return fingerPhalanges;
    }

    public void rotateOnXAxis(int ang) {
        this.r = new Rotate(ang, Rotate.X_AXIS);
        this.t = this.t.createConcatenation(this.r);
        this.getTransforms().clear();
        this.getTransforms().addAll(this.t);
        this.setTranslateX(this.startX);
        this.setTranslateX(this.startY);
        this.setTranslateZ(0);
    }

    public void rotateOnYAxis(int ang) {
        this.r = new Rotate(ang, Rotate.Y_AXIS);
        this.t = t.createConcatenation(this.r);
        this.getTransforms().clear();
        this.getTransforms().addAll(this.t);
        this.setTranslateX(this.startX);
        this.setTranslateX(this.startY);
        this.setTranslateZ(0);
    }

}

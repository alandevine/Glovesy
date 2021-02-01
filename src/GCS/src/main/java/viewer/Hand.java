package viewer;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

public class Hand extends Group {
    private Rotate r;

    private final double startX;
    private final double startY;

    private final double[] thumbRotations;
    private final double[] indexRotations;
    private final double[] middleRotations;
    private final double[] ringRotations;
    private final double[] pinkyRotations;

    private final Sphere[] homo = new Sphere[5];
    private final Sphere[] neutro = new Sphere[5];
    private final Sphere[] hetro = new Sphere[5];
    private final Sphere[] tip = new Sphere[5];
    private final Sphere wrist = new Sphere();
    private final Group phalanges = new Group();

    public Hand(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        int xOffset = -60;
        int[] yOffset = {10, 70, 80, 60, 40};

        double xPos;
        double yPos;

        this.setTranslateX(startX);
        this.setTranslateY(startY);

        wrist.setTranslateX(startX);
        wrist.setTranslateY(startY + yOffset[2] / 2.5d);
        wrist.setTranslateZ(0);
        wrist.setRadius(20);

        this.getChildren().add(wrist);

        for (int i = 0; i < homo.length; i++) {
            xPos = startX + xOffset;
            yPos = startY - yOffset[i];

            homo[i] = new Sphere();
            homo[i].setTranslateX(xPos);
            homo[i].setTranslateY(yPos);
            homo[i].setTranslateZ(0);
            homo[i].setRadius(10);
            util.setColor(homo[i], 1, 0, 0);

            neutro[i] = new Sphere();
            neutro[i].setTranslateX(xPos);
            neutro[i].setTranslateY(yPos - 40);
            neutro[i].setTranslateZ(0);
            neutro[i].setRadius(10);
            util.setColor(neutro[i], 0, 1, 0);

            hetro[i] = new Sphere();
            hetro[i].setTranslateX(xPos);
            hetro[i].setTranslateY(yPos - 80);
            hetro[i].setTranslateZ(0);
            hetro[i].setRadius(10);
            util.setColor(hetro[i], 0, 0, 1);
            this.getChildren().addAll(homo[i], neutro[i], hetro[i]);

            if (i != 0) {
                tip[i] = new Sphere();
                tip[i].setTranslateX(xPos);
                tip[i].setTranslateY(yPos - 120);
                tip[i].setTranslateZ(0);
                tip[i].setRadius(10);
                util.setColor(tip[i], 1, 0, 1);
                this.getChildren().add(tip[i]);
            }

            xOffset += 30;
        }

        thumbRotations = new double[] {90, 90, 90};
        indexRotations = new double[] {90, 90, 90, 90};
        middleRotations = new double[] {90, 90, 90, 90};
        ringRotations  = new double[] {90, 90, 90, 90};
        pinkyRotations = new double[] {90, 90, 90, 90};

        this.connectAll();
        this.getChildren().add(phalanges);
    }


    public Sphere[] getThumb() {
        return new Sphere[] {homo[0], neutro[0], hetro[0], null};
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

    private void connectAll() {
        this.phalanges.getChildren().clear();

        connectFinger(getThumb());
        connectFinger(getIndex());
        connectFinger(getMiddle());
        connectFinger(getRing());
        connectFinger(getPinky());
        addCylinder(this.wrist, getThumb()[0]);
        addCylinder(this.wrist, getIndex()[0]);
        addCylinder(this.wrist, getMiddle()[0]);
        addCylinder(this.wrist, getRing()[0]);
        addCylinder(this.wrist, getPinky()[0]);
    }

    private void connectFinger(Sphere[] finger) {
        for (int i = 0; i < finger.length - 1; i++) {
            if (finger[i + 1] != null)
                addCylinder(finger[i], finger[i + 1]);
        }
    }

    private void addCylinder(Sphere parent, Sphere child) {
        Cylinder cylinder;
        double distance;

        Point3D midpoint;
        cylinder = new Cylinder();
        Point3D a = new Point3D(parent.getTranslateX(), parent.getTranslateY(), parent.getTranslateZ());
        Point3D b = new Point3D(child.getTranslateX(), child.getTranslateY(), child.getTranslateZ());

        distance = a.distance(b);
        midpoint = a.midpoint(b);

        Rotate rotateAboutX = new Rotate(VectorMath.getAngleYZ(a, b), Rotate.X_AXIS);
        Rotate rotateAboutZ = new Rotate(VectorMath.getAngleXY(a, b), Rotate.Z_AXIS);
        Transform finalRotation = rotateAboutX.createConcatenation(rotateAboutZ);

        cylinder.setTranslateX(midpoint.getX());
        cylinder.setTranslateY(midpoint.getY());
        cylinder.setTranslateZ(midpoint.getZ());

        cylinder.setHeight(distance);
        cylinder.setRadius(5);

        cylinder.getTransforms().clear();
        cylinder.getTransforms().add(finalRotation);

        this.phalanges.getChildren().add(cylinder);
    }

    public void rotateOnXAxis(int ang) {
        this.r = new Rotate(ang, Rotate.X_AXIS);
        this.r.setPivotX(startX);
        this.r.setPivotY(startY);
        this.r.setPivotZ(0);
        this.getTransforms().addAll(r);
    }

    public void rotateOnYAxis(int ang) {
        this.r = new Rotate(ang, Rotate.Y_AXIS);
        this.r.setPivotX(startX);
        this.r.setPivotY(startY);
        this.r.setPivotZ(0);
        this.getTransforms().addAll(r);
    }

    /**
     * Method for contracting fingers at specific knuckles, and their subsequent knuckles.
     * @param angle angle of rotation in degrees
     * @param fingerName Either "thumb", "index", "middle", "ring" or "pinky".
     * @param index index of the joint to be rotated.
     */
    public void contractJoint(double angle, String fingerName, int index) {

        if (-90 <= angle && angle <= 90)
            throw new IllegalArgumentException("The angle argument must be a value between -90 and 90 degrees (inclusive).");

        Sphere[] finger;
        double newAngle;

        switch (fingerName) {
            case "thumb":
                finger = getThumb();
                newAngle = this.thumbRotations[index] - angle;
                this.thumbRotations[index] = newAngle;
                break;
            case "index":
                finger = getIndex();
                newAngle = this.indexRotations[index] - angle;
                this.indexRotations[index] = newAngle;
                break;
            case "middle":
                finger = getMiddle();
                newAngle = this.middleRotations[index] - angle;
                this.middleRotations[index] = newAngle;
                break;
            case "ring":
                finger = getIndex();
                newAngle = this.ringRotations[index] - angle;
                this.ringRotations[index] = newAngle;
                break;
            case "pinky":
                finger = getPinky();
                newAngle = this.pinkyRotations[index] - angle;
                this.pinkyRotations[index] = newAngle;
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (newAngle < -90)
            throw new IllegalArgumentException("The angle after rotation is lower than the minimal acceptable angle for a given finger");

        if (newAngle > 90)
            throw new IllegalArgumentException("The angle after rotation is higher than the maximum acceptable angle for a given finger");

        for (int i = index; i < finger.length - 1; i++) {
            Point3D pivot = util.sphereToPoint(finger[i]);
            Point3D point = util.sphereToPoint(finger[i + 1]);
            Point3D newPos = VectorMath.rotateAboutPoint(angle, pivot, point);

            double deltaY = -Math.abs(newPos.getY() - point.getY());
            double deltaZ = Math.abs(newPos.getZ() - point.getZ());

            finger[i + 1].setTranslateX(newPos.getX());
            finger[i + 1].setTranslateY(newPos.getY());
            finger[i + 1].setTranslateZ(newPos.getZ());

            for (int j = i + 2; j < finger.length; j++) {
                finger[j].setTranslateY(finger[j].getTranslateY() - deltaY);
                finger[j].setTranslateZ(finger[j].getTranslateZ() - deltaZ);
            }
        }
    }
}
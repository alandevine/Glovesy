package viewer;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class Finger extends Group {
    private double knuckleRotation = 0;
    private double neutroRotation = 0;
    private double hetroRotation = 0;

    private Sphere knuckle;
    private Sphere neutro;
    private Sphere hetro;
    private Sphere tip;

    private Cylinder knuckleToNeutro;
    private Cylinder neutroToHetro;
    private Cylinder hetroToTip;

    private final Group joints = new Group();

    double startX;
    double startY;
    double startZ;

    public Finger (double startX, double startY, double startZ) {
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;

        this.init();
    }

    private void init() {
        knuckle = util.addSphere(0,   new double[] {1, 0, 0}, this.startX, this.startY, this.startZ);
        neutro  = util.addSphere(40,  new double[] {0, 1, 0}, this.startX, this.startY, this.startZ);
        hetro   = util.addSphere(80,  new double[] {0, 0, 1}, this.startX, this.startY, this.startZ);
        tip     = util.addSphere(120, new double[] {1, 0, 1}, this.startX, this.startY, this.startZ);

        knuckleToNeutro = new Cylinder();
        neutroToHetro   = new Cylinder();
        hetroToTip      = new Cylinder();

        update();

        this.joints.getChildren().addAll(knuckle, neutro, hetro, tip);
        Group phalanges = new Group();
        phalanges.getChildren().addAll(knuckleToNeutro, neutroToHetro, hetroToTip);

        this.getChildren().addAll(joints, phalanges);
    }

    public void update() {
        util.updatePhalange(knuckle, neutro, knuckleToNeutro);
        util.updatePhalange(neutro, hetro, neutroToHetro);
        util.updatePhalange(hetro, tip, hetroToTip);
    }

    public Sphere getKnuckle() {
        return this.knuckle;
    }

    public void rotateKnuckle(double angle) {

        if (angle < -90 || 90 < angle)
            throw new IllegalArgumentException("The angle argument must be a value between -90 and 90 degrees (inclusive).");

        this.knuckleRotation += angle;

        if (this.knuckleRotation < 0)
            throw new IllegalArgumentException("The angle after rotation is lower than the minimal acceptable angle for a given finger");

        if (this.knuckleRotation > 90)
            throw new IllegalArgumentException("The angle after rotation is higher than the maximum acceptable angle for a given finger");

        rotateJoint(angle, 0);
        update();
    }

    public void rotateNeutro(double angle) {
        if (angle < -90 || 90 < angle)
            throw new IllegalArgumentException("The angle argument must be a value between -90 and 90 degrees (inclusive).");

        this.neutroRotation += angle;

        if (this.neutroRotation < 0)
            throw new IllegalArgumentException("The angle after rotation is lower than the minimal acceptable angle for a given finger");

        if (this.neutroRotation > 90)
            throw new IllegalArgumentException("The angle after rotation is higher than the maximum acceptable angle for a given finger");

        rotateJoint(angle, 1);
        update();
    }

    public void rotateHetro(double angle) {
        if (angle < -90 || 90 < angle)
            throw new IllegalArgumentException("The angle argument must be a value between -90 and 90 degrees (inclusive).");

        this.hetroRotation += angle;

        if (this.hetroRotation < 0)
            throw new IllegalArgumentException("The angle after rotation is lower than the minimal acceptable angle for a given finger");

        if (this.hetroRotation > 90)
            throw new IllegalArgumentException("The angle after rotation is higher than the maximum acceptable angle for a given finger");

        rotateJoint(angle, 2);
        update();
    }

    public void rotateJoint(double angle, int idx) {

        for (int i = idx; i < joints.getChildren().size() - 1; i++) {
            Point3D pivot = util.sphereToPoint((Sphere) this.joints.getChildren().get(i));
            Point3D point = util.sphereToPoint((Sphere) this.joints.getChildren().get(i + 1));
            Point3D newPos = VectorMath.rotateAboutPoint(angle, pivot, point);

            double deltaY = -Math.abs(newPos.getY() - point.getY());
            double deltaZ = Math.abs(newPos.getZ() - point.getZ());

            this.joints.getChildren().get(i + 1).setTranslateX(newPos.getX());
            this.joints.getChildren().get(i + 1).setTranslateY(newPos.getY());
            this.joints.getChildren().get(i + 1).setTranslateZ(newPos.getZ());

            if (angle > 0) {
                deltaY *= -1;
                deltaZ *= -1;
            }

            for (int j = i + 2; j < joints.getChildren().size(); j++) {
                joints.getChildren().get(j).setTranslateY(joints.getChildren().get(j).getTranslateY() + deltaY);
                joints.getChildren().get(j).setTranslateZ(joints.getChildren().get(j).getTranslateZ() + deltaZ);
            }
        }
    }
}

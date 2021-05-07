package viewer;

import javafx.scene.Group;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class Thumb extends Finger{

    private Sphere knuckle;
    private Sphere neutro;
    private Sphere hetro;

    private Cylinder knuckleToNeutro;
    private Cylinder neutroToHetro;

    private final Group joints = new Group();

    public Thumb(double startX, double startY, double startZ) {
        super(startX, startY, startZ);
    }

    public void init() {
        this.knuckle = util.addSphere(0,  new double[] {1, 0, 0}, startX, startY, startZ);
        this.neutro  = util.addSphere(40, new double[] {0, 1, 0}, startX, startY, startZ);
        this.hetro   = util.addSphere(80, new double[] {0, 0, 1}, startX, startY, startZ);

        this.knuckleToNeutro = new Cylinder();
        this.neutroToHetro   = new Cylinder();

        update();

        this.joints.getChildren().addAll(knuckle, neutro, hetro);
        Group phalanges = new Group();
        phalanges.getChildren().addAll(knuckleToNeutro, neutroToHetro);

        this.getChildren().addAll(joints, phalanges);
    }

    @Override
    public void update() {
        util.updatePhalange(this.knuckle, this.neutro, this.knuckleToNeutro);
        util.updatePhalange(this.neutro, this.hetro, this.neutroToHetro);
    }

    @Override
    public Sphere getKnuckle() {
        return this.knuckle;
    }

    @Override
    public void rotateJoint(double angle, int idx) {
        super.rotateJoint(angle, idx);
    }

    @Override
    public void rotateKnuckle(double angle) {
        super.rotateKnuckle(angle);
    }

    @Override
    public void rotateNeutro(double angle) {
        super.rotateNeutro(angle);
    }
}

package viewer;

import javafx.scene.Group;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Hand extends Group {
    private Rotate r;

    private final double startX;
    private final double startY;

    private final Finger thumb;
    private final Finger index;
    private final Finger middle;
    private final Finger ring;
    private final Finger pinky;

    private final Sphere wrist = new Sphere();

    public Hand(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        int[] yOffset = {10, 70, 80, 60, 40};

        wrist.setTranslateX(startX);
        wrist.setTranslateY(startY + yOffset[2] / 2.5d);
        wrist.setTranslateZ(0);
        wrist.setRadius(15);

        this.getChildren().add(wrist);

        thumb  = new Thumb(startX - 60, startY - 10, 0);
        thumb.init();
        index  = new Finger(startX - 30, startY - 70, 0);
        index.init();
        middle = new Finger(startX - 0,  startY - 80, 0);
        middle.init();
        ring   = new Finger(startX + 30, startY - 60, 0);
        ring.init();
        pinky  = new Finger(startX + 60, startY - 40, 0);
        pinky.init();

        Cylinder thumbToWrist = new Cylinder();
        Cylinder indexToWrist = new Cylinder();
        Cylinder middleToWrist = new Cylinder();
        Cylinder ringToWrist = new Cylinder();
        Cylinder pinkyToWrist = new Cylinder();

        util.updatePhalange(wrist, thumb. getKnuckle(),  thumbToWrist);
        util.updatePhalange(wrist, index. getKnuckle(),  indexToWrist);
        util.updatePhalange(wrist, middle.getKnuckle(), middleToWrist);
        util.updatePhalange(wrist, ring.  getKnuckle(),   ringToWrist);
        util.updatePhalange(wrist, pinky. getKnuckle(),  pinkyToWrist);

        this.getChildren().addAll(thumb, index, middle, ring, pinky);
        this.getChildren().addAll(thumbToWrist, indexToWrist, middleToWrist, ringToWrist, pinkyToWrist);

        this.setTranslateX(startX);
        this.setTranslateY(startY);
    }

    public void contractThumb(double angle) {
        this.thumb.rotateKnuckle(angle);
        this.thumb.rotateNeutro(angle);
        this.thumb.rotateHetro(angle);
    }

    public void contractIndex(double angle) {
        this.index.rotateKnuckle(angle);
        this.index.rotateNeutro(angle);
        this.index.rotateHetro(angle);
    }

    public void contractMiddle(double angle) {
        this.middle.rotateKnuckle(angle);
        this.middle.rotateNeutro(angle);
        this.middle.rotateHetro(angle);
    }

    public void contractRing(double angle) {
        this.ring.rotateKnuckle(angle);
        this.ring.rotateNeutro(angle);
        this.ring.rotateHetro(angle);
    }

    public void contractPinky(double angle) {
        this.pinky.rotateKnuckle(angle);
        this.pinky.rotateNeutro(angle);
        this.pinky.rotateHetro(angle);
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

    public void rotateOnZAxis(int ang) {
        this.r = new Rotate(ang, Rotate.Z_AXIS);
        this.r.setPivotX(startX);
        this.r.setPivotY(startY);
        this.r.setPivotZ(0);
        this.getTransforms().addAll(r);
    }
}
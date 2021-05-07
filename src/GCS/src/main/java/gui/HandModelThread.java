package gui;

import OSHandler.GloveState;
import viewer.Hand;

public class HandModelThread implements Runnable {

    private final Hand hand;
    private final GloveState gloveState;

    public HandModelThread(Hand hand, GloveState gloveState) {
        this.hand = hand;
        this.gloveState = gloveState;
    }

    @Override
    public void run() {

        double[] rotationVector;

        while (true) {
            rotationVector = this.gloveState.getRotationVector();

            hand.rotateOnXAxis((int) Math.toDegrees(rotationVector[0]));
            hand.rotateOnYAxis((int) Math.toDegrees(rotationVector[1]));
            hand.rotateOnZAxis((int) Math.toDegrees(rotationVector[2]));

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

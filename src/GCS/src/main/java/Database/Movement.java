package Database;

import java.util.Arrays;

public class Movement {
    // orientation of hand in R3
    private double[] xVector;
    private double[] yVector;
    private double[] zVector;

    // in ms-1
    private final double[] velocity;

    public Movement(double[] xVector, double[] yVector, double[] velocity) {
        if (!(xVector.length == 3))
            throw new IllegalArgumentException("The argument \"xVector\" requires a length of 3.");

        if (!(yVector.length == 3))
            throw new IllegalArgumentException("The argument \"yVector\" requires a length of 3.");

        if (!(this.dotProduct(xVector, yVector) == 0))
            throw new IllegalArgumentException("The arguments \"xVector\" and \"yVector\" must be orthogonal.");

        if (!(velocity.length == 3))
            throw new IllegalArgumentException("The argument \"yVector\" requires a length of 3.");

        if (Arrays.equals(xVector, yVector))
            throw new IllegalArgumentException("The arguments \"xVector\" and \"yVector\" cannot be equal.");

        this.xVector = xVector;
        this.yVector = yVector;
        this.zVector = this.crossProduct(xVector, yVector);

        this.velocity = velocity;
    }

    private double[] crossProduct(double[] v, double[] w) {
        double[] perpVector = new double[3];

        perpVector[0] = v[1] * w[2] - v[2] * w[1];
        perpVector[1] = v[2] * w[0] - v[0] * w[2];
        perpVector[2] = v[0] * w[1] - v[1] * w[0];

        return perpVector;
    }

    private double dotProduct(double[] v, double[] w) {
        double d = 0;

        for (int i = 0; i < v.length; i++)
            d += v[i] * w[i];

        return d;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public double[] getxVector() {
        return xVector;
    }

    public double[] getyVector() {
        return yVector;
    }

    public double[] getzVector() {
        return zVector;
    }

    public void setxVector(double[] xVector) {
        assert (xVector.length == 3);
        this.xVector = xVector;
        this.zVector = this.crossProduct(this.xVector, this.yVector);
    }

    public void setyVector(double[] yVector) {
        assert (yVector.length == 3);
        this.yVector = yVector;
        this.zVector = this.crossProduct(this.xVector, this.yVector);
    }

}

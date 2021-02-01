package viewer;

import javafx.geometry.Point3D;

public class VectorMath {

    /**
     * Method for finding the Angle of a given subject on the YZ Plane.
     * @param origin Origin Point
     * @param subject End Point
     * @return Angle in degrees
     */
    public static double getAngleYZ(Point3D origin, Point3D subject) {
        return Math.toDegrees(java.lang.Math.atan(((subject.getZ() - origin.getZ()) / (subject.getY() - origin.getY()))));
    }

    /**
     * Method for finding the Angle of a given subject on the XY Plane.
     * @param origin Origin Point
     * @param subject End Point
     * @return Angle in degrees
     */
    public static double getAngleXY(Point3D origin, Point3D subject) {
        return -Math.toDegrees(java.lang.Math.atan(((subject.getX() - origin.getX()) / (subject.getY() - origin.getY()))));
    }

    /**
     * Method for preforming a rotation on the X axis in R3.
     *
     * @param angle Angle in degrees.
     * @param pivot Pivot point for which the subject will rotate around.
     * @param subject Point that will be the subject of rotation.
     * @return Point where the object will be translated to after rotation.
     */
    public static Point3D rotateAboutPoint(double angle, Point3D pivot, Point3D subject) {

        // Math.cos & Math.sin both take radians as their arguments.
        angle = Math.toRadians(angle);

        double[][] rotationMatrix = new double[][] {
                {1, 0,                0},
                {0, Math.cos(angle), -Math.sin(angle)},
                {0, Math.sin(angle),  Math.cos(angle)}
            };

        subject = subject.subtract(pivot);

        return new Point3D(
                subject.getX() * rotationMatrix[0][0] + subject.getY() * rotationMatrix[0][1] + subject.getZ() * rotationMatrix[0][2],
                subject.getX() * rotationMatrix[1][0] + subject.getY() * rotationMatrix[1][1] + subject.getZ() * rotationMatrix[1][2],
                subject.getX() * rotationMatrix[2][0] + subject.getY() * rotationMatrix[2][1] + subject.getZ() * rotationMatrix[2][2]
            ).add(pivot);
    }
}

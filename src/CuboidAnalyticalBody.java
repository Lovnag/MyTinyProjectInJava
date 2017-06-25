/**
 * Created by thy humble Lovnag on 18.05.2017.
 */
public class CuboidAnalyticalBody implements AnalyticalBody {
    private Vector3 center;
    private double length;
    private double width;
    private double height;
    private Vector3 speed;
    private double mass;
    private Vector3[] dots;

    public CuboidAnalyticalBody(Vector3 center, double length, double width, double height, Vector3 speed, double mass) {
        this.center = center;
        this.length = length;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.mass = mass;
        this.dots = new Vector3[]{new Vector3(length / 2, width / 2, height / 2), new Vector3(length / 2, width / 2, -height / 2), new Vector3(length / 2, -width / 2, height / 2), new Vector3(-length / 2, width / 2, height / 2), new Vector3(-length / 2, -width / 2, height / 2), new Vector3(-length / 2, width / 2, -height / 2), new Vector3(length / 2, -width / 2, -height / 2), new Vector3(-length / 2, -width / 2, -height / 2)};
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Vector3 getSpeed() {
        return speed;
    }

    public double getMass() {
        return mass;
    }

    public void setSpeed(Vector3 speed) {
        this.speed = speed;
    }

    public double[] getDimensions() {
        double[] dims = new double[3];
        dims[0] = length;
        dims[1] = width;
        dims[2] = height;
        return dims;
    }


    public boolean cuboidCollisionDetection(CuboidAnalyticalBody cube) {
        for (int i = 0; i < 8; i++) {

            if ((dots[i].getX() < cube.getLength() / 2 + cube.getCenter().getX()) && (dots[i].getX() > -cube.getLength() / 2 + cube.getCenter().getX())) {
                if ((dots[i].getY() < cube.getWidth() / 2 + cube.getCenter().getY()) && (dots[i].getY() > -cube.getWidth() / 2 + cube.getCenter().getY())) {
                    if ((dots[i].getZ() < cube.getHeight() / 2 + cube.getCenter().getZ()) && (dots[i].getZ() > -cube.getHeight() / 2 + cube.getCenter().getZ())) {
                        return true;
                    }
                }
            }
        }
        return (false);
    }

    public boolean checkTheBorderX() {
        for (int i = 0; i < 8; i++) {
            if ((dots[i].getX() > 1) || (dots[i].getX() < -1)) {
                return true;
            }
        }
        return (false);
    }

    public boolean checkTheBorderY() {
        for (int i = 0; i < 8; i++) {
            if ((dots[i].getY() > 1) || (dots[i].getY() < -1)) {
                return true;
            }
        }
        return (false);
    }

    public boolean checkTheBorderZ() {
        for (int i = 0; i < 8; i++) {
            if ((dots[i].getZ() > 1) || (dots[i].getZ() < -1)) {
                return true;
            }
        }
        return (false);
    }

    public void hitTheBorders() {
        if (checkTheBorderX()) {
            setSpeed(new Vector3(-speed.getX(), speed.getY(), speed.getZ()));
        }
        if (checkTheBorderY()) {
            setSpeed(new Vector3(speed.getX(), -speed.getY(), speed.getZ()));
        }
        if (checkTheBorderZ()) {
            setSpeed(new Vector3(speed.getX(), speed.getY(), -speed.getZ()));
        }
    }

    public void centralCollision(AnalyticalBody anbdy) {
        Vector3 oldSpeed = speed;
        speed = speed.theMultiplication(mass - anbdy.getMass()).theAddition(anbdy.getSpeed().theMultiplication(2 * anbdy.getMass())).theMultiplication(1 / (mass + anbdy.getMass()));
        anbdy.setSpeed(anbdy.getSpeed().theMultiplication(anbdy.getMass() - mass).theAddition(oldSpeed.theMultiplication(2 * mass)).theMultiplication(1 / (mass + anbdy.getMass())));
    }

    public void elementalRunning(double t) {
        center = center.theAddition(speed.theMultiplication(t));
        hitTheBorders();
    }
}

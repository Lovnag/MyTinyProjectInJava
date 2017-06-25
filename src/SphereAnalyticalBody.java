
/**
 * Created by thy humble Lovnag on 29.04.2017.
 */

import java.math.*;

public class SphereAnalyticalBody implements AnalyticalBody {
    private Vector3 center;
    private double radius;
    private Vector3 speed;
    private double mass;

    public SphereAnalyticalBody(Vector3 center, double radius, Vector3 speed, double mass) {
        this.center = center;
        this.radius = radius;
        this.speed = speed;
        this.mass = mass;
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
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

    public boolean sphereCollisionDetection(SphereAnalyticalBody sph) {
        Vector3 centerVector = center.theSubstraction(sph.getCenter());
        return (centerVector.length() <= (radius + sph.getRadius()));
    }

    public boolean checkTheBorderX() {
    if ((Math.abs(center.getX() + radius) <= 1) || (Math.abs(center.getX() - radius) <= 1)) {
        return true;
    }
        return (false);
    }

    public boolean checkTheBorderY() {
        if ((Math.abs(center.getY() + radius) <= 1) || (Math.abs(center.getY() - radius) <= 1)) {
            return true;
        }
        return (false);
    }

    public boolean checkTheBorderZ() {
        if ((Math.abs(center.getZ() + radius) <= 1) || (Math.abs(center.getZ() - radius) <= 1)) {
            return true;
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

    public boolean cuboidCollisionDetection(CuboidAnalyticalBody cube) {
        Vector3 dotA = new Vector3(cube.getLength() / 2, cube.getWidth() / 2, cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotB = new Vector3(cube.getLength() / 2, cube.getWidth() / 2, -cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotC = new Vector3(cube.getLength() / 2, -cube.getWidth() / 2, cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotD = new Vector3(cube.getLength() / 2, -cube.getWidth() / 2, -cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotE = new Vector3(-cube.getLength() / 2, cube.getWidth() / 2, cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotF = new Vector3(-cube.getLength() / 2, cube.getWidth() / 2, -cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotG = new Vector3(-cube.getLength() / 2, -cube.getWidth() / 2, cube.getHeight() / 2).theAddition(cube.getCenter());
        Vector3 dotH = new Vector3(-cube.getLength() / 2, -cube.getWidth() / 2, -cube.getHeight() / 2).theAddition(cube.getCenter());

        Plane[] surfaces = new Plane[6];
        surfaces[0] = new Plane(dotA, dotB, dotC);
        surfaces[1] = new Plane(dotA, dotB, dotE);
        surfaces[2] = new Plane(dotA, dotC, dotE);
        surfaces[3] = new Plane(dotH, dotF, dotG);
        surfaces[4] = new Plane(dotH, dotG, dotD);
        surfaces[5] = new Plane(dotH, dotF, dotD);
        Line[] edges = new Line[12];
        edges[0] = new Line(dotA, dotB);
        edges[1] = new Line(dotA, dotC);
        edges[2] = new Line(dotA, dotE);
        edges[3] = new Line(dotB, dotD);
        edges[4] = new Line(dotB, dotF);
        edges[5] = new Line(dotC, dotD);
        edges[6] = new Line(dotC, dotG);
        edges[7] = new Line(dotD, dotH);
        edges[8] = new Line(dotE, dotF);
        edges[9] = new Line(dotE, dotG);
        edges[10] = new Line(dotF, dotH);
        edges[11] = new Line(dotG, dotH);
        for (int i = 0; i < 12; i++) {
            if (edges[i].getDot1().theSubstraction(center).theVectorProduct(edges[i].getDirection()).length() <= radius) {
                return true;
            }
        }
        for (int i = 0; i < 6; i++) {
            double fromCenterToSurface = Math.abs(center.getX() * surfaces[i].theNorm().getX() + center.getY() * surfaces[i].theNorm().getY() + center.getZ() * surfaces[i].theNorm().getZ() + 1) / surfaces[i].theNorm().length();
            if (fromCenterToSurface <= radius) {
                Line perpendicular = new Line(center, surfaces[i].theNorm(), true);
                if (Math.abs(perpendicular.dotWithDistanceFromTheCentralDot(fromCenterToSurface).theSubstraction(cube.getCenter()).getX()) <= cube.getLength() / 2) {
                    if (Math.abs(perpendicular.dotWithDistanceFromTheCentralDot(fromCenterToSurface).theSubstraction(cube.getCenter()).getY()) <= cube.getWidth() / 2) {
                        if (Math.abs(perpendicular.dotWithDistanceFromTheCentralDot(fromCenterToSurface).theSubstraction(cube.getCenter()).getZ()) <= cube.getHeight() / 2) {
                            return true;
                        }
                    }
                }
            }

        }
        return (false);
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

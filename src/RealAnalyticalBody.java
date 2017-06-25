/**
 * Created by thy humble Lovnag on 27.05.2017.
 */

import Jama.*;

import java.util.HashMap;

public class RealAnalyticalBody {
    private Vector3[] dots;
    private Integer[][] surfaces;
    private Vector3 linearSpeed;
    private Vector3 angularVelocity;
    private double mass;
    private HashMap<Integer, Integer[][]> surfacesForADot;
    private Vector3 coordinateCenter;

    public RealAnalyticalBody(Vector3[] dots, Integer[][] surfaces, Vector3 linearSpeed, Vector3 angularVelocity, Vector3 massCenter, double mass) {
        this.dots = dots;
        this.surfaces = surfaces;
        this.linearSpeed = linearSpeed;
        this.angularVelocity = angularVelocity;
        this.coordinateCenter = massCenter;
        this.mass = mass;
        this.coordinateCenter = new Vector3(0, 0, 0);

        surfacesForADot = new HashMap<Integer, Integer[][]>();
        for (int i = 0; i < dots.length; i++) {
            Integer[][] tempSurfaces = new Integer[surfaces.length][3];
            int current = 0;
            for (int j = 0; j < surfaces.length; j++) {
                if (((surfaces[j][0] == i) || (surfaces[j][1] == i)) || (surfaces[j][2] == i)) {
                    tempSurfaces[current] = surfaces[j];
                    current++;
                }
            }
            for (int j = 0; j < tempSurfaces.length; j++) {
                if (tempSurfaces[j] == null) {
                    tempSurfaces[j][0] = -1;
                    tempSurfaces[j][1] = -1;
                    tempSurfaces[j][2] = -1;
                }
            }
            surfacesForADot.put(i, tempSurfaces);
        }
    }


    public Vector3[] getDots() {
        return dots;
    }

    public Integer[][] getSurfaces() {
        return surfaces;
    }

    public Vector3 getLinearSpeed() {
        return linearSpeed;
    }

    public Vector3 getAngularVelocity() {
        return angularVelocity;
    }

    public double getMass() {
        return mass;
    }

    public Vector3 getCoordinateCenter() {
        return coordinateCenter;
    }

    public void setLinearSpeed(Vector3 linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    public void setAngularVelocity(Vector3 angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public boolean collisionCheck(RealAnalyticalBody body2) {
        Integer[][] tempSurfaces = body2.getSurfaces();
        Vector3[] tempDots = body2.getDots();
        for (int i = 0; i < dots.length; i++) {
            for (int h = 0; h < tempSurfaces.length; h++) {
                Plane analyticalSurface = new Plane(tempDots[tempSurfaces[h][0]], tempDots[tempSurfaces[h][1]], tempDots[tempSurfaces[h][2]]);
                for (int j = 0; j < surfacesForADot.get(i).length; j++) {
                    for (int p = 0; p < 3; p++) {
                        System.out.println(surfacesForADot.get(i)[0]);
                        try {
                        } catch (Exception NullPointerException) {
                            if ((dots[surfacesForADot.get(i)[j][p]]) != dots[i]) {
                                Line tempLine = new Line(dots[i], dots[surfacesForADot.get(i)[j][p]]);
                                Vector3 dotInThePlane = tempLine.dotWPlane(analyticalSurface);
                                if ((tempLine.checkIfBelongs(dotInThePlane)) && (analyticalSurface.checkIfBelongs(dotInThePlane))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public void collide(RealAnalyticalBody body2) {
        angularVelocity = new Vector3(-angularVelocity.getX(), -angularVelocity.getY(), -angularVelocity.getZ());
        body2.setAngularVelocity(new Vector3(-body2.getAngularVelocity().getX(), -body2.getAngularVelocity().getY(), -body2.getAngularVelocity().getZ()));
        linearSpeed = linearSpeed.theAddition(body2.getLinearSpeed());
        body2.setLinearSpeed(linearSpeed.theSubstraction(body2.getLinearSpeed()));
        linearSpeed = linearSpeed.theSubstraction(body2.getLinearSpeed());
    }

    public void elementalRunning(double t) {
        coordinateCenter.theAddition(linearSpeed.theMultiplication(t));
    }
}

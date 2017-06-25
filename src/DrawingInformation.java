/**
 * Created by thy humble Lovnag on 24.06.2017.
 */
public class DrawingInformation {
    private Vector3[] sphereCenter;
    private Vector3[] cubeCenter;
    private Vector3[] realBodyCenter;

    private double[] sphereRadius;
    private double[][] cuboidDimensions;
    private Vector3[][] realBodyDots;
    private Integer[][][] realBodySurfaces;

    private boolean SpheresAndCubes;

    public DrawingInformation(Vector3[] sphereCenter, Vector3[] cubeCenter, Vector3[] realBodyCenter, double[] sphereRadius, double[][] cuboidDimensions, Vector3[][] realBodyDots, Integer[][][] realBodySurfaces, boolean SpheresAndCubes) {
        this.sphereCenter = sphereCenter;
        this.cubeCenter = cubeCenter;
        this.realBodyCenter = realBodyCenter;
        this.sphereRadius = sphereRadius;
        this.cuboidDimensions = cuboidDimensions;
        this.realBodyDots = realBodyDots;
        this.realBodySurfaces = realBodySurfaces;
        this.SpheresAndCubes = SpheresAndCubes;
    }

    public Vector3[] getSphereCenter() {
        return sphereCenter;
    }

    public Vector3[] getCubeCenter() {
        return cubeCenter;
    }

    public Vector3[] getRealBodyCenter() {
        return realBodyCenter;
    }

    public double[] getSphereRadius() {
        return sphereRadius;
    }

    public double[][] getCuboidDimensions() {
        return cuboidDimensions;
    }

    public Vector3[][] getRealBodyDots() {
        return realBodyDots;
    }

    public Integer[][][] getRealBodySurfaces() {
        return realBodySurfaces;
    }

    public boolean isSpheresAndCubes() {
        return SpheresAndCubes;
    }
}

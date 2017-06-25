/**
 * Created by thy humble Lovnag on 20.06.2017.
 */

import java.util.Random;

public class AnalyticalInformation {
    private CuboidAnalyticalBody[] cuboidAnalyticalBodies;
    private RealAnalyticalBody[] realAnalyticalBodies;
    private SphereAnalyticalBody[] sphereAnalyticalBodies;
    private boolean SpheresAndCubes;
    private Random randomizer;


    public AnalyticalInformation(boolean spheresAndCubes) {
        SpheresAndCubes = spheresAndCubes;
        randomizer = new Random();
    }

    public void generate(int amount) {
        if (SpheresAndCubes) {
            generateSpheresAndCubes(amount);
        } else {
            generateRealStuff(amount);
        }

    }

    private void generateSpheresAndCubes(int amount) {
        Vector3[] sphereCenter = new Vector3[amount];
        Vector3[] cubeCenter = new Vector3[amount];
        double[] sphereRadius = new double[amount];
        double[][] cubeDimensions = new double[amount][3];
        sphereAnalyticalBodies = new SphereAnalyticalBody[amount];
        cuboidAnalyticalBodies = new CuboidAnalyticalBody[amount];

        for (int i = 0; i < amount; i++) {
            sphereCenter[i] = new Vector3((randomizer.nextDouble() - randomizer.nextDouble()), (randomizer.nextDouble() / -randomizer.nextDouble()), (randomizer.nextDouble() - randomizer.nextDouble()));
            cubeCenter[i] = new Vector3((randomizer.nextDouble() - randomizer.nextDouble()), (randomizer.nextDouble() - randomizer.nextDouble()), (randomizer.nextDouble() - randomizer.nextDouble()));
            sphereRadius[i] = randomizer.nextDouble() / 5 + 0.05;
            cubeDimensions[i][0] = randomizer.nextDouble() / 5 + 0.05;
            cubeDimensions[i][1] = randomizer.nextDouble() / 5 + 0.05;
            cubeDimensions[i][2] = randomizer.nextDouble() / 5 + 0.05;

            sphereAnalyticalBodies[i] = new SphereAnalyticalBody(sphereCenter[i], sphereRadius[i], new Vector3(((randomizer.nextDouble() / 5) - (randomizer.nextDouble() / 5)), ((randomizer.nextDouble() / 5) - (randomizer.nextDouble() / 5)), ((randomizer.nextDouble() / 50) - (randomizer.nextDouble() / 50))), randomizer.nextDouble() * 10);
            cuboidAnalyticalBodies[i] = new CuboidAnalyticalBody(cubeCenter[i], cubeDimensions[i][0], cubeDimensions[i][1], cubeDimensions[i][2], new Vector3(((randomizer.nextDouble() / 5 - randomizer.nextDouble() / 5)), ((randomizer.nextDouble() / 5) - (randomizer.nextDouble() / 5)), ((randomizer.nextDouble() / 50) - (randomizer.nextDouble() / 50))), randomizer.nextDouble() * 10);
        }


    }

    private void generateRealStuff(int sizes) {
        realAnalyticalBodies = new RealAnalyticalBody[2];
        Vector3[] dots1 = new Vector3[sizes];
        Vector3[] dots2 = new Vector3[sizes];
        Integer[][] surfaces1 = new Integer[sizes * sizes * sizes][3];
        Integer[][] surfaces2 = new Integer[sizes * sizes * sizes][3];


        for (int i = 0; i < sizes; i++) {
            dots1[i] = new Vector3(randomizer.nextDouble() + 0.3, randomizer.nextDouble() + 0.3, randomizer.nextDouble() + 0.3);
            dots2[i] = new Vector3(randomizer.nextDouble() - 0.7, randomizer.nextDouble() - 0.7, randomizer.nextDouble() - 0.7);
        }
        int totalCounter = 0;
        for (int i = 0; i < sizes; i++) {
            for (int j = 0; j < sizes; j++) {
                for (int h = 0; h < sizes; h++) {
                    surfaces1[totalCounter][0] = i;
                    surfaces2[totalCounter][0] = i;
                    surfaces1[totalCounter][1] = j;
                    surfaces2[totalCounter][1] = j;
                    surfaces1[totalCounter][2] = h;
                    surfaces2[totalCounter][2] = h;
                    totalCounter++;
                }
            }
        }

        RealAnalyticalBody body1 = new RealAnalyticalBody(dots1, surfaces1, new Vector3(Math.random() / 50, Math.random() / 50, Math.random() / 50), new Vector3(0, 0, 0), new Vector3(0, 0, 0), 0);
        RealAnalyticalBody body2 = new RealAnalyticalBody(dots2, surfaces2, new Vector3(Math.random() / 50, Math.random() / 50, Math.random() / 50), new Vector3(0, 0, 0), new Vector3(0, 0, 0), 0);

        realAnalyticalBodies[0] = body1;
        realAnalyticalBodies[1] = body2;
    }

    public void elementalRunning(double time, double previousTime, double startingTime) {
        if (!SpheresAndCubes) {
            for (int k = 0; k < realAnalyticalBodies.length; k++) {
                realAnalyticalBodies[k].elementalRunning(time - previousTime);
                for (int m = 0; m < realAnalyticalBodies.length; m++) {
                    if ((realAnalyticalBodies[k].collisionCheck(realAnalyticalBodies[m]))) {
                        realAnalyticalBodies[k].collide(realAnalyticalBodies[m]);
                    }
                }
            }
        } else {
            for (int i = 0; i < sphereAnalyticalBodies.length; i++) {
                sphereAnalyticalBodies[i].elementalRunning(time - previousTime);
                cuboidAnalyticalBodies[i].elementalRunning(time - previousTime);
            }

            for (int i = 0; i < sphereAnalyticalBodies.length; i++) {
                for (int j = i + 1; j < sphereAnalyticalBodies.length; j++) {
                    if ((sphereAnalyticalBodies[i].sphereCollisionDetection(sphereAnalyticalBodies[j])) && (i != j)) {
                        sphereAnalyticalBodies[i].centralCollision(sphereAnalyticalBodies[j]);
                    }
                }
                for (int j = 0; j < sphereAnalyticalBodies.length; j++) {
                    if (sphereAnalyticalBodies[i].cuboidCollisionDetection(cuboidAnalyticalBodies[j])) {
                        sphereAnalyticalBodies[i].centralCollision(cuboidAnalyticalBodies[j]);
                    }
                }
            }

            for (int i = 0; i < sphereAnalyticalBodies.length; i++) {
                for (int j = i + 1; j < sphereAnalyticalBodies.length; j++) {
                    if ((cuboidAnalyticalBodies[i].cuboidCollisionDetection(cuboidAnalyticalBodies[j])) && (i != j)) {
                        cuboidAnalyticalBodies[i].centralCollision(cuboidAnalyticalBodies[j]);
                    }
                }
            }
        }
    }

    public DrawingInformation generateDrawingInformation() {
        Vector3[] sphereCenter = null;
        Vector3[] cubeCenter = null;
        Vector3[] realBodyCenter = null;

        double[] sphereRadius = null;
        double[][] cuboidDimensions = null;
        Vector3[][] realBodyDots = null;
        Integer[][][] realBodySurfaces = null;
        if (SpheresAndCubes) {
            sphereCenter = new Vector3[sphereAnalyticalBodies.length];
            cubeCenter = new Vector3[cuboidAnalyticalBodies.length];
            sphereRadius = new double[sphereAnalyticalBodies.length];
            cuboidDimensions = new double[cuboidAnalyticalBodies.length][3];

            for (int i = 0; i < sphereAnalyticalBodies.length; i++) {
                sphereCenter[i] = sphereAnalyticalBodies[i].getCenter();
                sphereRadius[i] = sphereAnalyticalBodies[i].getRadius();
            }

            for (int i = 0; i < cuboidAnalyticalBodies.length; i++) {
                cubeCenter[i] = cuboidAnalyticalBodies[i].getCenter();
                cuboidDimensions[i] = cuboidAnalyticalBodies[i].getDimensions();
            }
        } else {
            realBodyCenter = new Vector3[realAnalyticalBodies.length];
            realBodyDots = new Vector3[realAnalyticalBodies.length][realAnalyticalBodies[0].getDots().length];
            realBodySurfaces = new Integer[realAnalyticalBodies.length][realAnalyticalBodies[0].getSurfaces().length][3];

            for (int i = 0; i < realAnalyticalBodies.length; i++) {
                realBodyCenter[i] = realAnalyticalBodies[i].getCoordinateCenter();
                realBodyDots[i] = realAnalyticalBodies[i].getDots();
                realBodySurfaces[i] = realAnalyticalBodies[i].getSurfaces();
            }
        }

        DrawingInformation info = new DrawingInformation(sphereCenter, cubeCenter, realBodyCenter, sphereRadius, cuboidDimensions, realBodyDots, realBodySurfaces, SpheresAndCubes);
        return info;
    }
}

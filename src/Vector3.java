/**
 * Created by thy humble Lovnag on 26.04.2017.
 * ( Just a small vector lib)
 */
public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double theDotProduct(final Vector3 vect) {
        return x * vect.getX() + y * vect.getY() + z * vect.getZ();
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 theVectorProduct(final Vector3 vect) {
        return new Vector3(y * vect.getZ() - z * vect.getY(), z * vect.getX() - x * vect.getZ(), x * vect.getY() - y * vect.getX());
    }

    public Vector3 theAddition(final Vector3 vect) {
        return new Vector3(x + vect.getX(), y + vect.getY(), z + vect.getZ());
    }

    public Vector3 theSubstraction(final Vector3 vect) {
        return new Vector3(x - vect.getX(), y - vect.getY(), z - vect.getZ());
    }

    public Vector3 theMultiplication(final double a) {
        return new Vector3(a * x, a * y, a * z);
    }
}

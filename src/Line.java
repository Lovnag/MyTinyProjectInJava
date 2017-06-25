/**
 * Created by thy humble Lovnag on 19.05.2017.
 */
public class Line {
    private Vector3 dot1;
    private Vector3 dot2;
    private Vector3 direction;

    public Vector3 getDot1() {
        return dot1;
    }

    public Vector3 getDot2() {
        return dot2;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public Line(Vector3 dot1, Vector3 dot2) {
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.direction = dot1.theSubstraction(dot2);
    }

    public Line(Vector3 dot1, Vector3 direction, boolean dir) {
        this.dot1 = dot1;
        this.direction = direction;
        this.dot2 = dot1.theAddition(direction);
    }

    public Vector3 dotWPlane(Plane pl) {
        Vector3 norm = pl.theNorm();
        double lamb = (-1 - dot1.theDotProduct(norm)) / (direction.theDotProduct(norm));
        return new Vector3(dot1.getX() + direction.getX() * lamb, dot1.getY() + direction.getY() * lamb, dot1.getZ() + direction.getZ() * lamb);
    }


    public Vector3 dotWithDistanceFromTheCentralDot(double distance) {
        if (((direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX() < dot1.getX()) && (direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX() > dot2.getX())) || ((direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX() < dot2.getX()) && (direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX() > dot1.getX()))) {
            return new Vector3(direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX(), direction.getY() * Math.sqrt(distance) / direction.length() + dot1.getY(), direction.getZ() * Math.sqrt(distance) / direction.length() + dot1.getZ());
        } else {
            return new Vector3(-direction.getX() * Math.sqrt(distance) / direction.length() + dot1.getX(), -direction.getY() * Math.sqrt(distance) / direction.length() + dot1.getY(), -direction.getZ() * Math.sqrt(distance) / direction.length() + dot1.getZ());
        }
    }

    public boolean checkIfBelongs(Vector3 dot) {
        if (((dot.getX() > dot1.getX()) && (dot.getX() < dot2.getX())) || (((dot.getX() > dot2.getX()) && (dot.getX() < dot1.getX())))) {
            if (((dot.getY() > dot1.getY()) && (dot.getY() < dot2.getY())) || (((dot.getY() > dot2.getY()) && (dot.getY() < dot1.getY())))) {
                if (((dot.getZ() > dot1.getZ()) && (dot.getZ() < dot2.getZ())) || (((dot.getZ() > dot2.getZ()) && (dot.getZ() < dot1.getZ())))) {
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }
}

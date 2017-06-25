/**
 * Created by thy humble Lovnag on 27.05.2017.
 */
public class Plane {
    private Vector3 dot1;
    private Vector3 dot2;
    private Vector3 dot3;

    public Plane(Vector3 dot1, Vector3 dot2, Vector3 dot3) {
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
    }

    public Vector3 theNorm() {
        double C = (-(dot3.getX() / dot1.getX()) - ((dot2.getX() - dot1.getX()) / (dot2.getY() * dot1.getX() - dot1.getY() * dot2.getX()) * (dot3.getX() * dot1.getY() - dot3.getY()) + 1)) / ((dot1.getZ() * dot2.getX() * (dot3.getX() * dot1.getY() - dot3.getY())) / (dot2.getY() * dot1.getX() - dot1.getY() * dot2.getX()) - (dot2.getZ() * dot1.getX() * (dot3.getX() * dot1.getY() - dot3.getY())) / (dot2.getY() * dot1.getX() - dot1.getY() * dot2.getX()) + dot1.getZ() * dot3.getX() / dot1.getX() - dot3.getZ());
        double B = (dot2.getX() + C * dot1.getZ() * dot2.getX() - dot1.getX() - C * dot2.getZ() * dot1.getX()) / (dot2.getY() * dot1.getX() - dot1.getY() * dot2.getX());
        double A = (-1 - B * dot1.getY() - C * dot1.getZ()) / dot1.getX();
        return new Vector3(A, B, C);
    }

    public boolean checkIfBelongs(Vector3 dot) {
        return dot1.theSubstraction(dot).theVectorProduct(dot2.theSubstraction(dot)).length() + dot3.theSubstraction(dot).theVectorProduct(dot2.theSubstraction(dot)).length() + dot1.theSubstraction(dot).theVectorProduct(dot3.theSubstraction(dot)).length() < dot1.theSubstraction(dot2).theVectorProduct(dot1.theSubstraction(dot3)).length();
    }
}

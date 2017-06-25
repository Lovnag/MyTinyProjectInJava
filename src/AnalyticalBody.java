/**
 * Created by thy humble Lovnag on 18.05.2017.
 */
public interface AnalyticalBody {

    Vector3 getCenter();

    Vector3 getSpeed();

    double getMass();

    void setSpeed(Vector3 speed);

    void centralCollision(AnalyticalBody anbdy);

}

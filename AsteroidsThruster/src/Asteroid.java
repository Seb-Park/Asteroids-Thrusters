import java.util.ArrayList;

public class Asteroid {

    public double dx, dy;
    public double centerMass;
    public ArrayList<Vector> vectors = new ArrayList<Vector>();
    public ArrayList<Integer> asteroidXPoints = new ArrayList<Integer>();
    public ArrayList<Integer> asteroidYPoints = new ArrayList<Integer>();

    public Asteroid(ArrayList<Vector> pVectors) {
//        asteroidXPoints = calculate based on the inputed vectors -- for each one use the recalculate method
//        asteroidYPoints =
        vectors = pVectors;
        dx = 3;
        dy = 3;

    }

    public void rotate(){

    }

}


//intersection method, translate method
//if(polygon.contains...)
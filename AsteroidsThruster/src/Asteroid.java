import java.util.ArrayList;

public class Asteroid {

    public double dx, dy;
    public double centerMassX;
    public double centerMassY;
    public ArrayList<Vector> vectors = new ArrayList<Vector>();
    public int[] asteroidXPoints;
    public int[] asteroidYPoints;

    public Asteroid(ArrayList<Vector> pVectors) {
        vectors = pVectors;
        asteroidXPoints = new int[pVectors.size()];
        asteroidYPoints = new int[pVectors.size()];
        for (int x = 0; x < pVectors.size(); x++) {
            asteroidXPoints[x] = (int) pVectors.get(x).xcom;
            asteroidYPoints[x] = (int) pVectors.get(x).ycom;
        }
        dx = 5;
        dy = 5;


    }

    public void rotate(double pAngle) {

        for (int x = 0; x < vectors.size(); x++) {
            asteroidXPoints[x] += dx;
            asteroidYPoints[x] += dy;
        }
    }

    public void move() {
        centerMassX= centerMassX+dx;
        centerMassY = centerMassY+dy;



    }
}


//intersection method, translate method
//if(polygon.contains...)
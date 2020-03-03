import java.util.ArrayList;

public class Asteroid {

    public double dx, dy;
    public double centerMassX;
    public double centerMassY;
    public ArrayList<Vector> vectors = new ArrayList<Vector>();
    public int[] asteroidXPoints;
    public int[] asteroidYPoints;

    public Asteroid(ArrayList<Vector> pVectors) {
        vectors = (ArrayList<Vector>) (pVectors.clone());
        asteroidXPoints = new int[pVectors.size()];
        asteroidYPoints = new int[pVectors.size()];
        for (int x = 0; x < pVectors.size(); x++) {
            asteroidXPoints[x] = (int) pVectors.get(x).xcom;
            asteroidYPoints[x] = (int) pVectors.get(x).ycom;
        }
        dx = 1;
        dy = 1;
    }

    public Asteroid(ArrayList<Vector> pVectors, int px, int py) {
        vectors = (ArrayList<Vector>) (pVectors.clone());
        asteroidXPoints = new int[pVectors.size()];
        asteroidYPoints = new int[pVectors.size()];
        for (int x = 0; x < pVectors.size(); x++) {
            asteroidXPoints[x] = (int) pVectors.get(x).xcom + px;
            asteroidYPoints[x] = (int) pVectors.get(x).ycom + py;
        }
        dx = (int)(Math.random() * 5 - 2.5);
        dy = (int)(Math.random() * 5 - 2.5);

        //add wrapping around the screen, rotation, and collisions

    }

    public void rotate(double pAngle) {

    }

    public void move() {
//        centerMassX = centerMassX + dx;
//        centerMassY = centerMassY + dy;
        for (int i = 0; i < vectors.size(); i++) {
            asteroidXPoints[i] += dx;
            asteroidYPoints[i] += dy;
        }

    }
}


//intersection method, translate method
//if(polygon.contains...)
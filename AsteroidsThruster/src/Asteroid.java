import java.awt.*;
import java.util.ArrayList;

public class Asteroid {

    public double dx, dy;
    public double centerMassX;
    public double centerMassY;
    public ArrayList<Vector> vectors = new ArrayList<Vector>();
    public int[] asteroidXPoints;
    public int[] asteroidYPoints;
    public Polygon collider;
    public int stage;

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

    public Asteroid(ArrayList<Vector> pVectors, int px, int py, int pStage) {
        vectors = (ArrayList<Vector>) (pVectors.clone());
        asteroidXPoints = new int[pVectors.size()];
        asteroidYPoints = new int[pVectors.size()];
        for (int x = 0; x < pVectors.size(); x++) {
            asteroidXPoints[x] = (int) pVectors.get(x).xcom + px;
            asteroidYPoints[x] = (int) pVectors.get(x).ycom + py;
        }
        dx = (int) (Math.random() * 5 - 2.5);
//        if(dx!=0) {
        dy = (int) (Math.random() * 5 - 2.5);
//        }
//        else{
//            dy = (int) (Math.random() * 2 +1);
//            if(Math.random()<.5){
//                dy=-dy;
//            }
//        }//this method of generation ensures no completely static asteroids
        centerMassX = px;
        centerMassY = py;
        stage = pStage;
        //findCenter();

        //add wrapping around the screen, rotation, and collisions

    }

    public Asteroid split() {
        System.out.println(centerMassX + "," + centerMassY);

        System.out.print("[");
        for (int i = 0; i < vectors.size(); i++) {
            System.out.print("["+vectors.get(i).angle + "," + vectors.get(i).magnitude + "],");
//            vectors.get(i).recalculate(vectors.get(i).angle, vectors.get(i).magnitude / 2);//divide size by two
            vectors.get(i).scale(.5);
        }
        System.out.println("]");

        asteroidXPoints = new int[vectors.size()];
        asteroidYPoints = new int[vectors.size()];
        for (int x = 0; x < vectors.size(); x++) {
            asteroidXPoints[x] = (int) (vectors.get(x).xcom + centerMassX);
            asteroidYPoints[x] = (int) (vectors.get(x).ycom + centerMassY);
        }

        System.out.print("[");
        for (int i = 0; i < vectors.size(); i++) {
            System.out.print("["+vectors.get(i).angle + "," + vectors.get(i).magnitude + "],");
        }
        System.out.println("]");

        System.out.println(centerMassX + "," + centerMassY);

        stage--;
//        System.out.println("Asteroid at ("+ centerMassX +"," + centerMassY + ") is at stage "+ stage);
        return new Asteroid(vectors, (int) centerMassX, (int) centerMassY, stage);
    }

    public void rotate(double pAngle) {

    }

    public void move() {
//        centerMassX = centerMassX + dx;
//        centerMassY = centerMassY + dy;
        for (int i = 0; i < vectors.size(); i++) {
            asteroidXPoints[i] += dx;
            asteroidYPoints[i] += dy;
            centerMassX += dx;
            centerMassY += dy;
        }
//        System.out.println(centerMassX + "," + centerMassY);
        collider = new Polygon(asteroidXPoints, asteroidYPoints, asteroidXPoints.length);

    }
}


//intersection method, translate method
//if(polygon.contains...)
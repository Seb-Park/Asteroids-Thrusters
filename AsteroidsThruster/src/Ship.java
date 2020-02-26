import java.util.ArrayList;
import java.util.Arrays;

public class Ship {

    public int xpos;
    public int ypos;
    public double dx;
    public double dy;
    public int width;
    public int height;
    public double velocity = 0;
    public double terminalvel = 5;
    public double acceleration = .2;
    public double drag = .0;
    public double ythrust;
    public double xthrust;
    public boolean isThrusting, isRight, isLeft, isHyperspace, isShooting, isAlive;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public int[] shipXPoints, shipYPoints;

    //    public Rectangle rec;
    public double angle;

    public Ship(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 60;
        height = 60;
        isThrusting = false;
        isAlive = true;
        isShooting = false;
        angle = 90;
        shipXPoints = new int[3];
        shipYPoints = new int[3];
        updateAngles();
    }

    public void reorient(double pAngle) {
        angle += pAngle;
        if (angle < 0) {
            angle += 360;
        }
        if (angle > 360) {
            angle = 360 - angle;
        }
//        System.out.println(angle);
    }

    public void thrust() {
        if (velocity < terminalvel) {
            velocity += (acceleration - drag);
        }
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
        ypos += ythrust;
        xpos += xthrust;
    }

    public void thrustNew() {
        ythrust = Math.sin(Math.toRadians(angle)) * acceleration;
        xthrust = Math.cos(Math.toRadians(angle)) * acceleration;

//        velocity = Math.sqrt(Math.pow(xpos, 2) + Math.pow(ypos, 2));

//        if (velocity < terminalvel) {
        dy += ythrust; // add the new vector and previous one together
        dx += xthrust;
//        }
        ypos += dy;
        xpos += dx;
    }

    public void drift() {
        if (dy >= 0) dy *= 0.01;//create drag proportional to the velocity
        if (dx >= 0) dx *= 0.01;
        ypos += dy;
        xpos += dx;
    }

    public void driftOld() {
        if (velocity > 0) {
            velocity -= drag;
        }
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
        ypos += ythrust;
        xpos += xthrust;
    }

    public void updateAngles() {
        shipXPoints[0] = (int) (Math.cos(Math.toRadians(angle)) * 25) + xpos;
        shipYPoints[0] = (int) (Math.sin(Math.toRadians(angle)) * 25) + ypos;
        shipXPoints[1] = (int) (Math.cos(Math.toRadians(angle + 230)) * 15) + xpos;
        shipYPoints[1] = (int) (Math.sin(Math.toRadians(angle + 230)) * 15) + ypos;
        shipXPoints[2] = (int) (Math.cos(Math.toRadians(angle + 130)) * 15) + xpos;
        shipYPoints[2] = (int) (Math.sin(Math.toRadians(angle + 130)) * 15) + ypos;
//        System.out.println(Arrays.toString(shipXPoints));
//        System.out.println(Arrays.toString(shipYPoints));
    }

    public void hyperspace() {
        xpos = (int) (Math.random() * 1000);
        ypos = (int) (Math.random() * 700);
        isHyperspace = false;
        int random = (int) (Math.random() * 10);
        if (random == 5) {
            isAlive = false;

        }
    }

    public void shoot() {
        bullets.add(new Bullet(xpos, ypos, angle));

    }

}







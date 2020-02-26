public class Bullet {

    public int xpos;
    public int ypos;
    public double ythrust;
    public double xthrust;
    public double angle;
    public double velocity = 10;

    public Bullet(int pXpos, int pYpos, double pAngle) {
        xpos = pXpos;
        ypos = pYpos;
        angle = pAngle;
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
    }

    public Bullet(int pXpos, int pYpos, double pAngle, double shipVelocity) {
        xpos = pXpos;
        ypos = pYpos;
        angle = pAngle;
        velocity = shipVelocity + velocity;
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
    }

    public Bullet(int pXpos, int pYpos, double pAngle, double shipdx, double shipdy) {
        xpos = pXpos;
        ypos = pYpos;
        angle = pAngle;
        ythrust = Math.sin(Math.toRadians(angle)) * velocity + shipdy;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity + shipdx;
    }

    public void move(){
        ypos += ythrust;
        xpos += xthrust;
    }
}

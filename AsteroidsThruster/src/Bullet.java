public class Bullet {

    public int xpos;
    public int ypos;
    public double ythrust;
    public double xthrust;
    public double angle;
    public double velocity = 5;

    public Bullet(int pXpos, int pYpos, double pAngle) {
        xpos = pXpos;
        ypos = pYpos;
        angle = pAngle;
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
    }

    public void move(){
        ypos += ythrust;
        xpos += xthrust;
    }
}

import java.awt.*;

public class Ship {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public double velocity = 0;
    public double terminalvel = 5;
    public double acceleration = .2;
    public double drag = .1;
    public double ythrust;
    public double xthrust;
    public boolean isThrusting, isRight, isLeft;

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
        angle = 90;
    }

    public void reorient(double pAngle){
        angle += pAngle;
        if(angle<0){
            angle += 360;
        }
        if(angle>360)
        {
            angle = 360-angle;
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

    public void drift(){
        if(velocity>0){
            velocity-=drag;
        }
        ythrust = Math.sin(Math.toRadians(angle)) * velocity;
        xthrust = Math.cos(Math.toRadians(angle)) * velocity;
        ypos += ythrust;
        xpos += xthrust;

    }
}







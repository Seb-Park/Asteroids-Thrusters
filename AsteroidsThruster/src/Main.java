import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class Main implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public Ship spaceship;

    public Image explosion;

    public int shootCounter;
    public int rCount, asteroidCount;
    public double rMagnitude, rAngle;

    public BufferStrategy bufferStrategy;

    public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    public ArrayList<Vector> vectorInput = new ArrayList<Vector>();


    public Main() {
        setUpGraphics();
        spaceship = new Ship(500, 350);
        shootCounter = 10;
        asteroidCount = 5;
        rCount = (int) (Math.floor(Math.random() * (1 + 7 - 3)) + 10);
        explosion = Toolkit.getDefaultToolkit().getImage("explosion.gif");

        for (int y = 0; y < asteroidCount; y++) {
            for (int x = 0; x < rCount; x++) {
                rMagnitude = Math.floor(Math.random() * (1 + 20 - 1)) + 20;
                rAngle += (int) (360 / (double) rCount);//We do 360/rcount so that we get even angles throughout the asteroid (rcount of them)
                vectorInput.add(new Vector(rAngle, rMagnitude));
            }
            asteroids.add(new Asteroid(vectorInput, (int) (Math.random() * 1000), (int) (Math.random() * 700)));
            vectorInput.clear();
        }
    }

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            checkIntersections();
            pause(20);
            if (shootCounter <= 10) {
                shootCounter++;
            }
        }
    }

    public void checkIntersections() {
        for (int i = 0; i < asteroids.size(); i++) {
            for (int j = 0; j < asteroids.size(); j++) {
                try {
                    if (asteroids.get(i).collider.contains(spaceship.bullets.get(j).xpos, spaceship.bullets.get(j).ypos)) {
                        spaceship.bullets.remove(j);
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public void moveThings() {
        for (int x = 0; x < asteroids.size(); x++) {
            asteroids.get(x).move();
//            if(asteroids.get(x).centerMassX<0){
//                asteroids.get(x).
//            }
        }

        if (spaceship.isThrusting) {
            spaceship.thrustNew();
        } else {
            spaceship.drift();
        }

        if (spaceship.isRight) {
            spaceship.reorient(5);
        }
        if (spaceship.isLeft) {
            spaceship.reorient(-5);
        }
        if (spaceship.isHyperspace) {
            spaceship.hyperspace();
        }

        if (spaceship.isShooting && shootCounter >= 10) {
            shootCounter = 0;
            spaceship.shoot();
        }

        for (int x = 0; x < spaceship.bullets.size(); x++) {
            spaceship.bullets.get(x).move();
            if (spaceship.bullets.get(x).xpos > 1000 || spaceship.bullets.get(x).xpos < 0 || spaceship.bullets.get(x).ypos < 0 || spaceship.bullets.get(x).ypos > 700) {
                spaceship.bullets.remove(x);
            }
        }

        spaceship.updateAngles();

        if (spaceship.xpos > frame.getWidth() + 11) {
            spaceship.xpos = -10;
        }
        if (spaceship.xpos < -11) {
            spaceship.xpos = frame.getWidth() + 10;
        }
        if (spaceship.ypos > frame.getHeight() + 11) {
            spaceship.ypos = -10;
        }
        if (spaceship.ypos < -11) {
            spaceship.ypos = frame.getHeight() + 10;
        }
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    private void setUpGraphics() {
        frame = new JFrame("Asteroid Thruster Example");
        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(this);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
//                Component c = (Component)evt.getSource();
                canvas.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            }
        });

        System.out.println("DONE graphic setup");


    }


    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        if (spaceship.isAlive) {
            g.setColor(Color.orange);
//            g.drawOval((int) (spaceship.xpos - Math.cos(Math.toRadians(spaceship.angle)) * 5), (int) (spaceship.ypos - Math.sin(Math.toRadians(spaceship.angle)) * 5), 15, 15);

            g.setColor(Color.white);
//            g.drawOval(spaceship.xpos, spaceship.ypos, 15, 15);

            g.drawPolygon(spaceship.shipXPoints, spaceship.shipYPoints, 4);
            if (spaceship.isThrusting) {
                g.setColor(Color.orange);
                g.fillPolygon(spaceship.engineXPoints, spaceship.engineYPoints, 4);
            }

            for (int x = 0; x < asteroids.size(); x++) {
                g.setColor(Color.white);
                g.drawPolygon(asteroids.get(x).asteroidXPoints, asteroids.get(x).asteroidYPoints, asteroids.get(x).asteroidXPoints.length);
            }

            for (int x = 0; x < spaceship.bullets.size(); x++) {
                g.setColor(Color.white);
                g.fillOval(spaceship.bullets.get(x).xpos, spaceship.bullets.get(x).ypos, 5, 5);
            }

            g.setColor(Color.white);
            g.drawString(spaceship.angle + "°", 20, 20);
//            g.drawString((double)Math.round(spaceship.velocity*10)/10 + "m/s", 20, 50);
        } else {
            g.setColor(Color.white);
            g.drawString("GAME OVER", 450, 600);
            g.drawImage(explosion, (int) spaceship.xpos, (int) spaceship.ypos, 75, 75, null);
        }

        g.dispose();

        bufferStrategy.show();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 39) {
            spaceship.isRight = true;
        }
        if (key == 37) {
            spaceship.isLeft = true;
        }
        if (key == 38 && spaceship.isAlive) {
            spaceship.isThrusting = true;
        }
        if (key == 72) {
            spaceship.isHyperspace = true;
        }
        if (key == 32) {
            spaceship.isShooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 39) {
            spaceship.isRight = false;
        }
        if (key == 37) {
            spaceship.isLeft = false;
        }
        if (key == 38) {
            spaceship.isThrusting = false;
        }
        if (key == 72) {
            spaceship.isHyperspace = false;
        }
        if (key == 32) {
            spaceship.isShooting = false;
        }
    }
}
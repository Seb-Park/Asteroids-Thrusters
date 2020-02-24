import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class Main implements Runnable, KeyListener {
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public Ship spaceship;

    public BufferStrategy bufferStrategy;

    public Main() {
        setUpGraphics();

        spaceship = new Ship(500, 350);

    }

    public static void main(String[] args) {
        Main ex = new Main();
        new Thread(ex).start();
    }

    public void run() {
        while (true) {
            moveThings();
            render();
            pause(20);
        }
    }


    public void moveThings() {
        if(spaceship.isThrusting){
            spaceship.thrust();
        }
        else{
            spaceship.drift();
        }
        if(spaceship.isRight){
            spaceship.reorient(5);
        }
        if(spaceship.isLeft){
            spaceship.reorient(-5);
        }

        if(spaceship.xpos>frame.getWidth()+11){
            spaceship.xpos = -10;
        }
        if(spaceship.xpos<-11){
            spaceship.xpos = frame.getWidth()+10;
        }
        if(spaceship.ypos>frame.getHeight()+11){
            spaceship.ypos = -10;
        }
        if(spaceship.ypos<-11){
            spaceship.ypos = frame.getHeight()+10;
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

        g.setColor(Color.orange);
        g.drawOval((int)(spaceship.xpos - spaceship.xthrust), (int)(spaceship.ypos - spaceship.ythrust), 15, 15);

        g.setColor(Color.white);
        g.drawOval(spaceship.xpos, spaceship.ypos, 15, 15);

        g.drawString(spaceship.angle+"°",20,20);

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
        if (key == 38) {
            spaceship.isThrusting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == 39){
            spaceship.isRight = false;
        }
        if(key == 37){
            spaceship.isLeft = false;
        }
        if(key == 38){
            spaceship.isThrusting = false;
        }
    }
}
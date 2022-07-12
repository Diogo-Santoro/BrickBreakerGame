import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 31;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 250;
    private int ballXdir = -1;
    private int ballYdir = -2;

    //constructor
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,690,590);


        //padlle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550,100,8);

        //ball
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(ballposX,ballposY,20,20);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){
                ballXdir = -ballXdir;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >=600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
    }


}
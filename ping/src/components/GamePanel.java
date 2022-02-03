package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

    private static final long serialVersionUID = 5194319078091921553L;
    Timer t = new Timer(5, this); // The timer controls the redrawing in some way.
    Ball pingBall = new Ball(100, 600);
    Paddle playerPaddle = new Paddle(getWidth(), 50);
    Paddle compPaddle = new Paddle(0, 100);
    PingCom com = new PingCom(compPaddle);
    //constructor, initializes keylistener and other things
    public GamePanel() {
        t.start();
        this.setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    //this is what to redraw in the animation
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        pingBall.paint(this, g);
        playerPaddle.paint(this, g);
        compPaddle.paint(this, g);
    }

    // describes the behavior of all objects at every redraw
    public void actionPerformed(ActionEvent e) {
        if (pingBall.getx() < -50 || pingBall.getx() > 1600) {
            pingBall = new Ball(100, 100); 
        }
        pingBall.updateBall();
        playerPaddle.update(getHeight());
        com.update(getHeight());
        if(pingBall.gety() > getHeight() - 60 /*&& dy > 0*/){ // when ball y hits an edge, reverse y velocity (commented out todel unless a problem popps up)
            pingBall.bounceHorizontal();
            com.setTarget(pingBall.getx(), pingBall.gety(), pingBall.getdx(), pingBall.getdy());
        }
        if(pingBall.gety() < 20 /*&& dy < 0*/){
            pingBall.bounceHorizontal();
            com.setTarget(pingBall.getx(), pingBall.gety(), pingBall.getdx(), pingBall.getdy());
        }
        if(playerPaddle.isTouching(pingBall.getx() +20, pingBall.gety())){
            pingBall.bounceVertical(playerPaddle.placeOnPaddle(pingBall.gety()));
            com.setTarget(pingBall.getx(), pingBall.gety(), pingBall.getdx(), pingBall.getdy());
        }
        if(compPaddle.isTouching(pingBall.getx() -20, pingBall.gety())){
            pingBall.bounceVertical(compPaddle.placeOnPaddle(pingBall.gety()));
        }
        repaint();
    }

    public void keyPressed(KeyEvent e){// handles key controls
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            playerPaddle.pdlUp();
        }
        if(code == KeyEvent.VK_DOWN){
            playerPaddle.pdlDown();
        }
        /*
        if(code == KeyEvent.VK_W){
            compPaddle.pdlUp();
        }
        if(code == KeyEvent.VK_S){
            compPaddle.pdlDown();
        }*/

    }

	public void keyTyped(KeyEvent e) {
        		
	}

    public void keyReleased(KeyEvent e) { //stopps movement at key release
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN){
            playerPaddle.pdlRelease();
        }
        /*if(code == KeyEvent.VK_W || code == KeyEvent.VK_S){
            compPaddle.pdlRelease();
        }*/
    }

    public void initPdls(){
        playerPaddle = new Paddle(getWidth()-20, 100);
        compPaddle = new Paddle(5, 100);
        com = new PingCom(compPaddle);
        com.setTarget(1, 1, 1, 1);

    }
}
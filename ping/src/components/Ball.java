package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Ball{
    private  double x;
    private double dx;
    private double y;
    private double dy;
    Shape bal;
    private BufferedImage IMG = null;

    public Ball(double x, double y){
        this.x = x;
        this.y = y;
        dy = 0.1;
        dx = 1;
        bal = new Ellipse2D.Double(x - 20, y - 20, 40, 40);
        IMG = GetImage.retrieve("ball.jpg");
    }

    //TODO: Draw an image file.
    public void paint(Graphics2D g2){
        bal = new Ellipse2D.Double(x - 20, y - 20, 40, 40);
        g2.fill(bal);
    }

    public void paint(JPanel panel, Graphics g){
        g.drawImage(IMG, (int)x-20, (int)y-20, panel);
    }
    
    public void updateBall(){
        x += dx; //apply x velocity to x position
        y += dy; //apply y velocity to y position
        
        if(dy == 0){
            //System.out.println("(" + x + "," + y + ") dx:" + dx);
        }
    }
    public void bounceVertical(int bounceAngle){
        //System.out.println(dx);
        dx = -(dx + (Math.abs(bounceAngle * 0.005)));
        //System.out.println(dx);
        dy += (-bounceAngle * 0.05);
    }

    public void bounceHorizontal(){
        dy = -dy;
    }

    public double getx(){
        return x;
    }

    public double gety(){
        return y;
    }

    public double getdy(){
        return dy;
    }

    public double getdx(){
        return dx;
    }
}
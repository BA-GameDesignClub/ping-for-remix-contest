package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Paddle{
    public final double X;
    public double y;
    public double dy;
    Shape pdl;
    private BufferedImage IMG = null;

    public Paddle(double x, double y){
        this.X = x;
        this.y = y;
        dy = 0;
        pdl = new Rectangle2D.Double(X, y, 10, 100);
        IMG = GetImage.retrieve("paddle.jpg");
    }

    //TODO: draw an image file
    public void paint(Graphics2D g2){
        pdl = new Rectangle2D.Double(X, y, 10, 100);
        g2.fill(pdl);
        //g2.drawImage();
    }
    public void paint(JPanel panel, Graphics g){
        g.drawImage(IMG, (int)X, (int)y, panel);
    }
    public void update(int height){
        if(y < 0){
            y = 0;
        }
        else if(y > height-100){
            y = height - 100;
        }
        else{
            y += dy;
        }
    }
    public void pdlUp(){
        dy = -1;
    }
    public void pdlDown(){
        dy = 1;
    }
    public void pdlRelease(){
        dy = 0;
    }
    public boolean isTouching(double inx, double iny){
        return ((inx > this.X) && (inx < this.X + 10)) && ((iny > this.y) && (iny < this.y + 100));
    }
    public int placeOnPaddle(double iny){
        return (int)(this.y + 50 - iny);
    }
    public double getx(){
        return X;
    }
    public double gety(){
        return y;
    }
    //TODO: delete
    public double getdy(){
        return dy;
    }
}
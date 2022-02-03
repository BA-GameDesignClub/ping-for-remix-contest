package components;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class GetImage {
    public static BufferedImage retrieve(String in) { // 'in' formatted as filename.jpg
        BufferedImage out = null;
        try {
            out = ImageIO.read(new File("./images/" + in));
        } catch (IOException e) {
            System.out.println("unable to retrieve " + in);
        }
        return out;
    }
    /* Main method to test 
    public static void main(String[] args) {
        if(retrieve("ball.jpg") == null){
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
    }*/
}
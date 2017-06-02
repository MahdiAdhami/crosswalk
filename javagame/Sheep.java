
package javagame;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Sheep {
    
    private float[] PositionOfSheep;
    private int Rate;      
    private BufferedImage ImageOfSheep;
    
  
    
    public Sheep(float[] position , int rate)
    {
        PositionOfSheep = position;
        Rate = rate;
        try {
            ImageOfSheep = ImageIO.read(new File(Const.PATH + "\\src\\Resources.Sheep\\Sheep.png"));
                    } catch (IOException ex) {
            Logger.getLogger(InitGraphic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public float getXPosition()
    {
        return PositionOfSheep[0];
    }
    
    public float getYPosition()
    {
        return PositionOfSheep[1];
    }
    
    public void setRate(int r)
    {
        Rate=r;
    }
    
    public int getRate()
    {
        return Rate;
    }
    
    public void MoveInCrossWalk()
    {
        
    }
    public BufferedImage getImage()
    {
        return ImageOfSheep;
    }
    
    public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if(keyCode ==  KeyEvent.VK_UP)
    {
       System.out.print("pressed");
    }
    else if(keyCode == KeyEvent.VK_DOWN)
    {
       
    }
    
}
    
}

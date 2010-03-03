import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Foreground extends Item
{
    BufferedImage foreground;
    
    Foreground (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        try
        {
            foreground = ImageIO.read(getClass().getResource("images/foreground.png"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
        }
    }
    
    void draw(Graphics2D g)
    {
        g.drawImage(foreground, 0, world.abs_y() - y(), null);
    }
}

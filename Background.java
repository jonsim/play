import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Background extends Item
{
    BufferedImage background;
    
    Background (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
	    
	    try
        {
            background = ImageIO.read(new File("images/background.jpg"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
        }
    }
	
	void draw(Graphics2D g)
    {
        g.setColor(new Color(74, 139, 238));
        g.fillRect(0, 0, world.width, world.height);
        g.drawImage(background, 0, world.abs_y() - y(), null);
    }
}
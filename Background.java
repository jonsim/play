import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

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
            background = ImageIO.read(new File("images/background.png"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
        }
    }
	
	void draw(Graphics2D g)
    {
        Color gradient_top = new Color(74, 139, 238);
        Color gradient_bottom = new Color(184,206,239);
        
        g.setColor(gradient_top);
        g.fillRect(0, 0, world.width, world.height);
        
        g.setPaint(new GradientPaint(0, world.abs_y() - y() + (height - 250), gradient_bottom, 0, world.abs_y() - y(), gradient_top));
        g.fillRect(0, world.abs_y() - y(), world.width, height - 250);
        
        g.drawImage(background, 0, world.abs_y() - y() + (height - 350), null);
    }
}
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
        int abs_y = world.abs_y() - y();
        
        Color top = new Color(74, 139, 238);
        Color bottom = new Color(184,206,239);
        
        g.setColor(top);
        g.fillRect(0, 0, world.width, world.height);
        
        g.setPaint(new GradientPaint(0, abs_y + (height - 250), bottom, 0, abs_y, top));
        g.fillRect(0, abs_y, world.width, height - 250);
        
        g.drawImage(background, 0, world.abs_y() - y() + (height - 350), null);
    }
}

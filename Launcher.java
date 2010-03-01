import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Launcher extends Item
{        
	static BufferedImage fish;
	static BufferedImage fish2;
	static BufferedImage fish_dead;
	
	float life = 1;
	boolean dying = false;
	
	Launcher (World world, int x, int y, int width, int height)
	{
	    super(world, x, y, width, height);
	    
	    if (fish == null)
	    {
	        try
	        {
                fish = ImageIO.read(new File("images/launchers/fish.png"));
                fish_dead = ImageIO.read(new File("images/launchers/fish_dead.png"));
                fish2 = ImageIO.read(new File("images/launchers/fish2.png"));
            }
            catch (IOException e)
            {
                System.err.println("Error reading image!");
            }
	    }
	}
    
    void draw(Graphics2D g)
    {
        if (dying)
        {
            g.drawImage(fish_dead, x(), world.abs_y() - y(), null);
        }
        else
        {
            if (this instanceof MegaLauncher)
                g.drawImage(fish2, x(), world.abs_y() - y(), null);
            else
                g.drawImage(fish, x(), world.abs_y() - y(), null);
        }
    }
    
    void update (double time_delta)
    {
        super.update(time_delta);
        
        if (dying)
        {
            life -= time_delta;
            
            if (life <= 0)
            {
                purge = true;
            }
        }
        else if (collision())
        {
            launch_player();
                
            dying = true;
            fixed = false;
        }
    }
    
    void launch_player()
    {
        if (world.player.VPPS > world.player.y_speed)
            world.player.y_speed = world.player.VPPS; 
    }
    
    boolean collision()
    {
        Player p = world.player;
        
        int t = p.y();
        int b = p.y() + p.height;
        int l = p.x();
        int r = p.x() + p.width;
        
        if (inShape(x(), y(), t, b, l, r))
            return true;
        
        if (inShape(x() + width, y(), t, b, l, r))
            return true;
            
        if (inShape(x(), y() + height, t, b, l, r))
            return true;
            
        if (inShape(x() + width, y() + height, t, b, l, r))
            return true;
            
        return false;
    }

    boolean inShape (int x, int y, int top, int bottom, int left, int right)
    {
        return (x > left && x < right) && (y > top && y < bottom);
    }
}
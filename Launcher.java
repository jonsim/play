import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class Launcher extends Item
{        
    static BufferedImage fish;
    static BufferedImage fish_dead;
    
    float life = 1;
    boolean dying = false;
    
    Launcher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish == null)
        {
            fish = load_image("images/launchers/fish.png");
            fish_dead = load_image("images/launchers/fish_dead.png");
        }
    }
    
    BufferedImage load_image(String s)
    {
        try
        {
            return ImageIO.read(new File(s));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
        }
        
        return null;
    }
    
    BufferedImage fish()
    {
        return fish;
    }
    
    void draw(Graphics2D g)
    {
        BufferedImage fish = dying ? fish_dead : fish();
        g.drawImage(fish, x(), world.abs_y() - y(), null);
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
        {
            world.player.y_speed = world.player.VPPS; 
        }
    }
    
    boolean collision()
    {
        Player p = world.player;
        
        int t = p.y();
        int b = p.y() + p.height;
        int l = p.x();
        int r = p.x() + p.width;
        
        if (inShape(x(), y(), t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x() + width, y(), t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x(), y() + height, t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x() + width, y() + height, t, b, l, r))
        {
            return true;
        }
        
        return false;
    }
    
    boolean inShape (int x, int y, int top, int bottom, int left, int right)
    {
        return (x > left && x < right) && (y > top && y < bottom);
    }
}

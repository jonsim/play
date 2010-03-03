import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.util.Date;
import java.util.Random;

class Launcher extends Item
{        
    static BufferedImage fish_left;
    static BufferedImage fish_right;
    static BufferedImage fish_dead_left;
    static BufferedImage fish_dead_right;
    
    static Random random;
    
    float life = 1;
    boolean dying = false;
    boolean direction;
    
    static int score = 1;
    
    Launcher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (random == null)
        {
            Date date = new Date();
            random = new Random(date.getTime());
        }
        
        if (fish_left == null)
        {
            fish_left = load_image("images/launchers/fish_left.png");
            fish_right = load_image("images/launchers/fish_right.png");
            fish_dead_left = load_image("images/launchers/fish_dead_left.png");
            fish_dead_right = load_image("images/launchers/fish_dead_right.png");
        }
        
        direction = random.nextBoolean();
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
        return direction ? fish_right : fish_left;
    }
    
    BufferedImage fish_dead()
    {
        return direction ? fish_dead_right : fish_dead_left;
    }
    
    void draw(Graphics2D g)
    {
        BufferedImage fish = dying ? fish_dead() : fish();
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
            world.hud.add_score(score);
            
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

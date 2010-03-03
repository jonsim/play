import javax.swing.JComponent;

import java.util.ArrayList;

import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

// Key Codes: http://livedocs.adobe.com/flash/9.0/main/wwhelp/wwhimpl/common/html/wwhelp.htm?context=LiveDocs_Parts&file=00001136.html

class Player extends JComponent
{
    boolean fixed = false;
    
    World world;
    
    float x, y;
    int width = 48;
    int height = 40;
    
    float y_speed = 0;
    final int VPPS = 600;
    final int HPPS = 400;
    
    int y_initial;
    int max_height = 0;
    
    boolean key_up = false;
    boolean key_left = false;
    boolean key_right = false;
    boolean on_ground = true;
    
    BufferedImage penguin_leftup;
    BufferedImage penguin_leftdown;
    BufferedImage penguin_rightup;
    BufferedImage penguin_rightdown;
    BufferedImage penguin_centreup;
    BufferedImage penguin_centredown;
    BufferedImage game_over;
    
    boolean dying = false;
    
    float death_counter = 0;
    
    Player (World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        
        y_initial = y;
    }
    
    void setPlayerImages(int i)
    {
        try
        {   
            penguin_leftup = ImageIO.read(new File("images/player/penguin" + i + "_leftup.png"));
            penguin_leftdown = ImageIO.read(new File("images/player/penguin" + i + "_leftdown.png"));
            penguin_rightup = ImageIO.read(new File("images/player/penguin" + i + "_rightup.png"));
            penguin_rightdown = ImageIO.read(new File("images/player/penguin" + i + "_rightdown.png"));
            penguin_centreup = ImageIO.read(new File("images/player/penguin" + i + "_centreup.png"));
            penguin_centredown = ImageIO.read(new File("images/player/penguin" + i + "_centredown.png"));
            
            game_over = ImageIO.read(new File("images/game_over.png"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading images!");
        }
    }
    
    void draw(Graphics2D g)
    {
        BufferedImage penguin;
        
        if (y_speed > 0)
        {
            if (key_right)
            {
                penguin = penguin_rightup;
            }
            else if (key_left)
            {
                penguin = penguin_leftup;
            }
            else
            {
                penguin = penguin_centreup;
            }
        }
        else
        {
            if (key_right)
            {
                penguin = penguin_rightdown;
            }
            else if (key_left)
            {
                penguin = penguin_leftdown;
            }
            else
            {
                penguin = penguin_centredown;
            }
        }
        
        g.drawImage(penguin, x(), 300 - height, null);
        
        if (dying)
        {
            g.drawImage(game_over, 72, 100, null);
        }
    }
    
    void update (double time_delta)
    {
        if (!on_ground)
        {
            y_speed += world.gravity * time_delta;
        }
        
        if (x < 0)
        {
            x = 0;
        }
        else if (x > 500 - width)
        {
            x = 500 - width;
        }
        
        if (key_left  && x > 0)
        {
            x -= HPPS * time_delta;
        }
        
        if (key_right && x < 500 - width)
        {
            x += HPPS * time_delta;
        }
        
        if (key_up && on_ground)
        {
            y_speed = VPPS;
            on_ground = false;
        }
        
        y += y_speed * time_delta;
        
        if (y > y_initial)
        {
            on_ground = false;
        }
        else if (y < y_initial)
        {
            y = y_initial;
            y_speed = 0;
            on_ground = true;
        }
        
        if (y() > max_height)
        {
            max_height = y();
        }
        else if (y() < (max_height - 1200) && y() > 600)
        {
            y = 600;
            dying = true;
        }
        
        if (on_ground && (max_height - y_initial) > 200)
        {
            dying = true;
        }
        
        if (dying && on_ground)
        {
            if (death_counter >= 2)
            {
                world.new_game = true;
            }
            
            death_counter += time_delta;
        }
    }
    
    int x()
    {
        return Math.round(x);
    }
    
    int y()
    {
        return Math.round(y);
    }
}

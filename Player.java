import java.util.ArrayList;
import java.awt.event.*;

import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

// Key Codes: http://livedocs.adobe.com/flash/9.0/main/wwhelp/wwhimpl/common/html/wwhelp.htm?context=LiveDocs_Parts&file=00001136.html

class Player implements KeyListener
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
    
    Player (World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        
        y_initial = y;
        
        try
        {
            penguin_leftup = ImageIO.read(new File("images/player/penguin_leftup.png"));
            penguin_leftdown = ImageIO.read(new File("images/player/penguin_leftdown.png"));
            penguin_rightup = ImageIO.read(new File("images/player/penguin_rightup.png"));
            penguin_rightdown = ImageIO.read(new File("images/player/penguin_rightdown.png"));
            penguin_centreup = ImageIO.read(new File("images/player/penguin_centreup.png"));
            penguin_centredown = ImageIO.read(new File("images/player/penguin_centredown.png"));
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
            world.hud.score = (max_height - 300 - height) / 50;
        }
        else if (y() < (max_height - 600) && y() > 600)
        {
            y = 600;
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
    
    public void keyTyped (KeyEvent e)
    {
    }
    
    public void keyPressed (KeyEvent e)
    {
        if (keyUp(e))
        {
            key_up = true;
        }
        
        if (keyLeft(e))
        {
            key_left = true;
        }
        
        if (keyRight(e))
        {
            key_right = true;
        }
    }
    
    public void keyReleased (KeyEvent e)
    {
        if (keyUp(e))
        {
            key_up = false;
        }
        
        if (keyLeft(e))
        {
            key_left = false;
        }
        
        if (keyRight(e))
        {
            key_right = false;
        }
    }
    
    boolean keyUp(KeyEvent e)
    {
        return e.getKeyCode() == 38 || e.getKeyCode() == 32 || e.getKeyCode() == 87;
    }
    
    boolean keyLeft(KeyEvent e)
    {
        return e.getKeyCode() == 37 || e.getKeyCode() == 65;
    }
    
    boolean keyRight(KeyEvent e)
    {
        return e.getKeyCode() == 39 || e.getKeyCode() == 68;
    }
}

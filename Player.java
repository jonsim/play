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
    
    final int VPPS = 500;
    final int HPPS = 300;
        
    // double friction = 0.4;
    float x, y;
    int width = 48;
    int height = 48;
    float y_speed = 0;
    // TODO: Might not need y

    boolean key_up = false;
    boolean key_left = false;
    boolean key_right = false;
    boolean on_ground = true;
    
    World world;
    
    BufferedImage penguin_left;
    BufferedImage penguin_right;
    BufferedImage penguin_up;
    BufferedImage penguin_down;
    
    Player (World world, int x, int y)
	{
	    this.world = world;
	    this.x = x;
	    this.y = y;
	    
	    try
        {
            penguin_left = ImageIO.read(new File("images/player/penguin_left.png"));
            penguin_right = ImageIO.read(new File("images/player/penguin_right.png"));
            penguin_up = ImageIO.read(new File("images/player/penguin_up.png"));
            penguin_down = ImageIO.read(new File("images/player/penguin_down.png"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading images!");
        }
	}

	void draw(Graphics2D g)
    {        
        if (key_right)
            g.drawImage(penguin_right, x(), 300 - height, null);
        else if (key_left)
            g.drawImage(penguin_left,  x(), 300 - height, null);
        else if (y_speed > 0)
            g.drawImage(penguin_up,  x(), 300 - height, null);
        else
            g.drawImage(penguin_down,  x(), 300 - height, null);
    }
    
    void update (float time_delta)
    {    
        if (!on_ground)
            y_speed += world.gravity;
        
        if (x < 0)
            x = 0;
        else if (x > 500 - width)
            x = 500 - width;
        
        if (key_left  && x > 0)
            x -= HPPS * time_delta;
        if (key_right && x < 500 - width)
            x += HPPS * time_delta;
            
        if (key_up && on_ground)
        {
            y_speed = VPPS;
            on_ground = false;
        }
        
        y += y_speed * time_delta;
        
        if (y > 300 + height)
        {
            on_ground = false;
        }
        else if (y < 300 + height)
        {
            y = 300 + height;
            y_speed = 0;
            on_ground = true;
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
            key_up = true;
            
        if (keyLeft(e))
            key_left = true;
            
        if (keyRight(e))
            key_right = true;
    }
    
    public void keyReleased (KeyEvent e)
    {
        if (keyUp(e))
            key_up = false;
            
        if (keyLeft(e))
            key_left = false;
            
        if (keyRight(e))
            key_right = false;
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

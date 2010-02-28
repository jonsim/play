import java.util.ArrayList;
import java.awt.event.*;

import java.awt.Graphics2D;
import java.awt.Color;

// Key Codes: http://livedocs.adobe.com/flash/9.0/main/wwhelp/wwhimpl/common/html/wwhelp.htm?context=LiveDocs_Parts&file=00001136.html

class Player implements KeyListener
{
    boolean fixed = false;
    
    final int VPPS = 500;
    final int HPPS = 300;
        
    // double friction = 0.4;
    float x, y;
    int width = 40;
    int height = 40;
    float y_speed = 0;
    // TODO: Might not need y

    boolean key_up = false;
    boolean key_left = false;
    boolean key_right = false;
    boolean on_ground = true;
    
    World world;
    
    Player (World world, int x, int y)
	{
	    this.world = world;
	    this.x = x;
	    this.y = y;
	}

	void draw(Graphics2D g)
    {
        g.setColor(new Color(255, 0, 255)); // Pink
        g.fillRect(x(), y(), 40, 40);
    }
    
    void update (float time_delta)
    {    
        if (!on_ground)
            y_speed += world.gravity;
        
        if (x < 0)
            x = 0;
        else if (x > 460)
            x = 460;
        
        if (key_left  && x > 0)
            x -= HPPS * time_delta;
        if (key_right && x < 460)
            x += HPPS * time_delta;
            
        if (key_up && on_ground)
        {
            y_speed = VPPS;
            on_ground = false;
        }
        
        world.y += y_speed * time_delta;
        
        if (world.y > 0)
        {
            on_ground = false;
        }
        
        if (world.y() < 0)
        {
            world.y = 0;
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

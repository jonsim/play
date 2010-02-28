import java.awt.Graphics2D;
import java.awt.Color;

class Item
{
	World world;
	
	// Position
    float x;
    float y;
    int width;
    int height;
	
	// Speed
	float x_speed = 0;
	float y_speed = 0;
	
	boolean dead = false;
	boolean fixed = true;
	
	Item (World world, int x, int y, int width, int height)
	{
	    this.world = world;
	    this.width = width;
	    this.height = height;
	    this.x = x;
	    this.y = y;
	}
	
	void draw(Graphics2D g)
    {
        g.setColor(new Color(0, 0, 0)); // Black
        g.fillRect(x(), world.abs_y() - y(), width, height);
    }
	
	void update(float time_delta)
	{
	    // Gravity
	    if (!fixed)
        {
            y_speed -= world.gravity * 0.5;
            
            y += y_speed * time_delta;

            // TODO change this
            if (y > (300 + height))
                dead = true;
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
	
	// For debugging purposes only
	void print()
	{
	    System.out.println("x:" + x() + " y:" + y() + " width:" + width + " height:" + height);
	}
}
import java.awt.Graphics2D;
import java.awt.Color;

class Item
{
	World world;

	boolean dead = false;
	boolean fixed = true;
	
	float yspeed = 0;
	
	// Position
    float x;
    float y;
    int width;
    int height;
	
	// Speed
	private float x_speed = 0;
	private float y_speed = 0;
	
	Item (World world, int x, int y, int width, int height)
	{
	    this.world = world;
	    this.width = width;
	    this.height = height;
	    this.x = x;
	    this.y = y;
	}
	
	// For debugging purposes only
	void print()
	{
	    System.out.println("x:" + x() + " y:" + y() + " width:" + width + " height:" + height);
	}
	
	void draw(Graphics2D g)
    {
        g.setColor(new Color(0, 0, 0)); // Black
        g.fillRect(x(), y() + world.y(), width, height);
    }
	
	void update(float time_delta)
	{
	    // something goes here
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
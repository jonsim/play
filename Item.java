import java.awt.Graphics2D;
import java.awt.Color;

abstract class Item
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
    
    boolean purge = false;
    boolean fixed = true;
    
    Item (World world, int x, int y, int width, int height)
    {
        this.world = world;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    abstract void draw(Graphics2D g);
    
    void update(double time_delta)
    {
        // Gravity
        if (!fixed)
        {
            y_speed += world.gravity * 0.5 * time_delta;
            
            y += y_speed * time_delta;
            
            if (y <= (300 + height))
            {
                purge = true;
            }
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

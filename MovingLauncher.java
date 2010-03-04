import java.awt.image.BufferedImage;

class MovingLauncher extends Launcher
{
    static BufferedImage fish4_left;
    static BufferedImage fish4_right;
    
    MovingLauncher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish4_left == null)
        {
            fish4_left = loadImage("fish4_left.png");
            fish4_right = loadImage("fish4_right.png");
        }
    }
    
    int score()
    {
        return 10;
    }
    
    int multiplier()
    {
        return 2;
    }
    
    BufferedImage fish()
    {
        return direction ? fish4_right : fish4_left;
    }
    
    void update(double time_delta)
    {
        super.update(time_delta);
        
        if (!dying)
        {
            if (direction)
            {
                x += 100 * time_delta;
            }
            else
            {
                x -= 100 * time_delta;
            }
            
            if (x < 0)
            {
                x = 0;
                direction = true;
            }
            
            if (x > 470)
            {
                x = 470;
                direction = false;
            }
        }
    }
}

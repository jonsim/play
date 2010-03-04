import java.awt.image.BufferedImage;

class UberLauncher extends Launcher
{
    static BufferedImage fish3_left;
    static BufferedImage fish3_right;
    
    UberLauncher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish3_left == null)
        {
            fish3_left = loadImage("fish3_left.png");
            fish3_right = loadImage("fish3_right.png");
        }
    }
    
    int score()
    {
        return 5;
    }
    
    int multiplier()
    {
        return 5;
    }
    
    BufferedImage fish()
    {
        return direction ? fish3_right : fish3_left;
    }
}
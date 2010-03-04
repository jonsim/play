import java.awt.image.BufferedImage;

class MegaLauncher extends Launcher
{
    static BufferedImage fish2_left;
    static BufferedImage fish2_right;
    
    MegaLauncher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish2_left == null)
        {
            fish2_left = loadImage("fish2_left.png");
            fish2_right = loadImage("fish2_right.png");
        }
    }
    
    int score()
    {
        return 2;
    }
    
    int multiplier()
    {
        return 2;
    }
    
    BufferedImage fish()
    {
        return direction ? fish2_right : fish2_left;
    }
}

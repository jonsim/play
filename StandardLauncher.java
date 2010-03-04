import java.awt.image.BufferedImage;

class StandardLauncher extends Launcher
{
    static BufferedImage fish_left;
    static BufferedImage fish_right;
    
    StandardLauncher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish_left == null)
        {
            fish_left = loadImage("fish_left.png");
            fish_right = loadImage("fish_right.png");
        }
    }
    
    int score()
    {
        return 1;
    }
    
    int multiplier()
    {
        return 1;
    }
    
    BufferedImage fish()
    {
        return direction ? fish_right : fish_left;
    }
}

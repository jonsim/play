import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class UberLauncher extends Launcher
{
    static BufferedImage fish3_left;
    static BufferedImage fish3_right;
    
    static int score = 5;
    
    UberLauncher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish3_left == null)
        {
            fish3_left = load_image("images/launchers/fish3_left.png");
            fish3_right = load_image("images/launchers/fish3_right.png");
        }
    }
    
    BufferedImage fish()
    {
        return direction ? fish3_right : fish3_left;
    }
    
    void launch_player()
    {
        if ((world.player.VPPS * 5) > world.player.y_speed)
        {
            world.player.y_speed = world.player.VPPS * 5;
        }
    }
}
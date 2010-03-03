import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class MegaLauncher extends Launcher
{
    static BufferedImage fish2_left;
    static BufferedImage fish2_right;
    
    static int score = 2;
    
    MegaLauncher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish2_left == null)
        {
            fish2_left = load_image("images/launchers/fish2_left.png");
            fish2_right = load_image("images/launchers/fish2_right.png");
        }
    }
    
    BufferedImage fish()
    {
        return direction ? fish2_right : fish2_left;
    }
    
    void launch_player()
    {
        if ((world.player.VPPS * 2) > world.player.y_speed)
        {
            world.player.y_speed = world.player.VPPS * 2;
        }
    }
}

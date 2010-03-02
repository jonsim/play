import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class UberLauncher extends Launcher
{
    static BufferedImage fish3;
    
    UberLauncher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish3 == null)
        {
            fish3 = load_image("images/launchers/fish3.png");
        }
    }
    
    BufferedImage fish()
    {
        return fish3;
    }
    
    void launch_player()
    {
        if ((world.player.VPPS * 5) > world.player.y_speed)
        {
            world.player.y_speed = world.player.VPPS * 5;
        }
    }
}
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class MegaLauncher extends Launcher
{
    static BufferedImage fish2;
    
    MegaLauncher (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish2 == null)
        {
            fish2 = load_image("images/launchers/fish2.png");
        }
    }
    
    BufferedImage fish()
    {
        return fish2;
    }
    
    void launch_player()
    {
        if ((world.player.VPPS * 2) > world.player.y_speed)
        {
            world.player.y_speed = world.player.VPPS * 2;
        }
    }
}

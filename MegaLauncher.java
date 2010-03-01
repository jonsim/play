import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class MegaLauncher extends Launcher
{        	
	MegaLauncher (World world, int x, int y, int width, int height)
	{
	    super(world, x, y, width, height);
	}
    
    void launch_player()
    {
        world.player.y_speed = world.player.VPPS * 2;
    }
}
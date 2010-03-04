import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Foreground extends Item
{
    BufferedImage foreground;
    
    Foreground (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        foreground = loadImage("foreground.png");
    }
    
    void draw(Graphics2D g)
    {
        g.drawImage(foreground, 0, world.abs_y() - y(), null);
    }
}

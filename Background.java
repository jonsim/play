import java.awt.*;

class Background extends Item
{
    Background (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
    }
    
    void draw(Graphics2D g)
    {
        int abs_y = world.abs_y() - y();
        
        Color top = new Color(74, 139, 238);
        Color bottom = new Color(184, 206, 239);
        
        g.setColor(top);
        g.fillRect(0, 0, world.width, world.height);
        
        g.setPaint(new GradientPaint(0, abs_y + height, bottom, 0, abs_y, top));
        g.fillRect(0, abs_y, world.width, height);
    }
}

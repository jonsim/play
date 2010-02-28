import java.awt.Color;
import java.awt.Graphics2D;

class Launcher extends Item
{        
	float life = 1;
	boolean dying = false;
	
	Launcher (World world, int x, int y, int width, int height)
	{
	    super(world, x, y, width, height);
	}
    
    void draw(Graphics2D g)
    {
        g.setColor(new Color(255, 255, 0)); // Vermillion
        g.fillRect(x(), y() + world.y(), width, height);
    }
    
    void update (float time_delta)
    {
        if (!fixed)
        {
            yspeed -= world.gravity * 0.5;
            
            y += yspeed * time_delta;

            // TODO change this
            if (y > (300 - height))
                dead = true;
        }
        
        if (dying)
        {
            life -= time_delta;
            
            if (life <= 0)
            {
                dead = true;
            }
        }
        else if (collision())
        {
            world.player.yspeed = world.player.VPPS;
            dying = true;
            fixed = false;
        }
    }
    
    boolean collision()
    {
        Player p = world.player;
        int t = p.y() - world.y();
        int b = (p.y() + p.height) - world.y();
        int l = p.x();
        int r = p.x() + p.width;
        
        if (inShape(x(),         y(),          t, b, l, r)
        ||  inShape(x() + width, y(),          t, b, l, r)
        ||  inShape(x(),         y() + height, t, b, l, r)
        ||  inShape(x() + width, y() + height, t, b, l, r))
            return true;
        else
            return false;
    }

    boolean inShape (int x, int y, int top, int bottom, int left, int right)
    {
        return (x > left && x < right) && (y > top && y < bottom);
    }
}
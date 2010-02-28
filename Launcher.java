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
        g.setColor(new Color(255, 0, 0));
        g.fillRect(x(), y() + world.y(), width, height);
    }
    
    void update (float time_delta)
    {
        super.update(time_delta);
        
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
            world.player.y_speed = world.player.VPPS;
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
        
        if (inShape(x(), y(), t, b, l, r))
            return true;
        
        if (inShape(x() + width, y(), t, b, l, r))
            return true;
            
        if (inShape(x(), y() + height, t, b, l, r))
            return true;
            
        if (inShape(x() + width, y() + height, t, b, l, r))
            return true;
            
        return false;
    }

    boolean inShape (int x, int y, int top, int bottom, int left, int right)
    {
        return (x > left && x < right) && (y > top && y < bottom);
    }
}
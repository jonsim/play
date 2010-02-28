import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

class Hud
{
    int x;
    int y;
    
    int score = 0;
    
    World world;
    
	Hud (World world, int x, int y)
	{
	    this.world = world;
	    this.x = x;
	    this.y = y;
	}
	
	void draw(Graphics2D g)
	{
	    g.setFont(new Font("Verdana", Font.BOLD, 24));
	    g.setColor(new Color(255, 0, 0));
	    g.drawString("Score: " + score, x, y);
	}
}
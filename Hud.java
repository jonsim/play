import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

class Hud extends Item
{    
    int total_score = 0;
    
    Hud (World world, int x, int y)
    {
        super(world, x, y);
    }
    
    void add_score(int score)
    {
        total_score += score * multiplier();
    }
    
    int total_score()
    {
        return Math.round(total_score);
    }
    
    int multiplier()
    {
        return Math.round((world.player.max_height - world.player.y_initial) / 500);
    }
    
    void draw(Graphics2D g)
    {
        g.setFont(new Font("Verdana", Font.BOLD, 24));
        g.setColor(new Color(35, 52, 73));
        g.drawString(total_score() + " (x" + multiplier() + ")", x, y);
    }
}

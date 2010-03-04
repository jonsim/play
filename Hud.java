import java.awt.*;

class Hud extends Item
{
    int total_score = 0;
    
    Hud(World world, int x, int y)
    {
        super(world, x, y);
    }
    
    void addScore(int score)
    {
        total_score += score * multiplier();
    }
    
    int totalScore()
    {
        return Math.round(total_score);
    }
    
    int multiplier()
    {
        return Math.round((world.player.max_height - world.player.y_initial) / 500) + 1;
    }
    
    void draw(Graphics2D g)
    {
        g.setFont(new Font("Verdana", Font.BOLD, 24));
        g.setColor(new Color(35, 52, 73));
        g.drawString(totalScore() + " (" + ((world.player.max_height - world.player.y_initial) / 50) + "m)", x, y);
    }
}

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

class Player
{
    World world;
    
    float x, y;
    int width = 48;
    int height = 40;
    
    float y_speed = 0;
    final int VPPS = 600;
    final int HPPS = 400;
    
    int y_initial;
    int max_height = 0;
    
    boolean key_up = false;
    boolean key_left = false;
    boolean key_right = false;
    boolean on_ground = true;
    
    BufferedImage penguin_leftup;
    BufferedImage penguin_leftdown;
    BufferedImage penguin_rightup;
    BufferedImage penguin_rightdown;
    BufferedImage penguin_centreup;
    BufferedImage penguin_centredown;
    BufferedImage game_over;
    
    boolean dying = false;
    float death_counter = 0;
    
    Player(World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
        
        y_initial = y;
    }
    
    void setPlayerImages(int i)
    {
        penguin_leftup = loadImage("penguin" + i + "_leftup.png");
        penguin_leftdown = loadImage("penguin" + i + "_leftdown.png");
        penguin_rightup = loadImage("penguin" + i + "_rightup.png");
        penguin_rightdown = loadImage("penguin" + i + "_rightdown.png");
        penguin_centreup = loadImage("penguin" + i + "_centreup.png");
        penguin_centredown = loadImage("penguin" + i + "_centredown.png");
        
        game_over = loadImage("game_over.png");
    }
    
    BufferedImage loadImage(String s)
    {
        try
        {
            return ImageIO.read(getClass().getResource("images/player/" + s));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
            System.exit(1);
        }
        
        return null;
    }
    
    void draw(Graphics2D g)
    {
        BufferedImage penguin;
        
        if (y_speed > 0)
        {
            if (key_right)
            {
                penguin = penguin_rightup;
            }
            else if (key_left)
            {
                penguin = penguin_leftup;
            }
            else
            {
                penguin = penguin_centreup;
            }
        }
        else
        {
            if (key_right)
            {
                penguin = penguin_rightdown;
            }
            else if (key_left)
            {
                penguin = penguin_leftdown;
            }
            else
            {
                penguin = penguin_centredown;
            }
        }
        
        g.drawImage(penguin, x(), 300 - height, null);
        
        if (dying)
        {
            g.drawImage(game_over, 72, 100, null);
        }
    }
    
    void update(double time_delta)
    {
        // Gravity
        if (!on_ground)
        {
            y_speed += world.gravity * time_delta;
        }
        
        // Player can't move off the screen horizontally
        if (x < 0)
        {
            x = 0;
        }
        else if (x > 500 - width)
        {
            x = 500 - width;
        }
        
        // Move left
        if (key_left  && x > 0)
        {
            x -= HPPS * time_delta;
        }
        
        // Move right
        if (key_right && x < 500 - width)
        {
            x += HPPS * time_delta;
        }
        
        // Jump
        if (key_up && on_ground)
        {
            y_speed = VPPS;
            on_ground = false;
        }
        
        // Update y
        y += y_speed * time_delta;
        
        // Determine whether player is on ground
        if (y > y_initial)
        {
            on_ground = false;
        }
        else if (y < y_initial)
        {
            y = y_initial;
            y_speed = 0;
            on_ground = true;
        }
        
        // Record max height
        if (y() > max_height)
        {
            max_height = y();
        }
        else if (y() < (max_height - 1200) && y() > 600)
        {
            y = 600;
            dying = true;
        }
        
        // If the player has hit a launcher but is now on the ground
        if (on_ground && (max_height - y_initial) > 200)
        {
            dying = true;
        }
        
        // Wait a bit on the ground when game over
        if (dying && on_ground)
        {
            if (death_counter >= 2)
            {
                world.new_game = true;
            }
            
            death_counter += time_delta;
        }
    }
    
    int x()
    {
        return Math.round(x);
    }
    
    int y()
    {
        return Math.round(y);
    }
}

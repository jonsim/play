import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

class Cloud extends Item
{
    static ArrayList<BufferedImage> clouds = new ArrayList<BufferedImage>();
    
    Date date = new Date();
    static Random random;
    
    int cloud;
    
    Cloud (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (clouds.size() == 0)
        {
            for (int i = 0; i < 10; i++)
            {
                clouds.add(loadImage("cloud" + i + ".png"));
            }
            
            random = new Random(date.getTime());
        }
        
        cloud = random.nextInt(clouds.size());
    }
    
    BufferedImage loadImage(String s)
    {
        return super.loadImage("clouds/" + s);
    }
    
    void draw(Graphics2D g)
    {
        g.drawImage(clouds.get(cloud), x(), world.abs_y() - y(), null);
    }
}

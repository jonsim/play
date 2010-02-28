import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

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
    	        try
    	        {
                    clouds.add(ImageIO.read(new File("images/clouds/cloud" + i + ".png")));
                }
                catch (IOException e)
                {
                    System.err.println("Error reading image!");
                }
            }
            
            random = new Random(date.getTime());
	    }
	    
	    cloud = random.nextInt(clouds.size());
	}
	
	void draw(Graphics2D g)
    {
        g.drawImage(clouds.get(cloud), x(), y() + world.y(), null);
    }
}